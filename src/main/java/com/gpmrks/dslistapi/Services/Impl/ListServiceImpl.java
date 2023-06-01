package com.gpmrks.dslistapi.Services.Impl;

import com.gpmrks.dslistapi.Dto.ListDTO;
import com.gpmrks.dslistapi.Dto.ListForm;
import com.gpmrks.dslistapi.Dto.MinimalGameInfoDTO;
import com.gpmrks.dslistapi.Entities.ListOfGames;
import com.gpmrks.dslistapi.Exceptions.ListNotFoundException;
import com.gpmrks.dslistapi.Projections.MinimalGameInfoProjection;
import com.gpmrks.dslistapi.Repositories.ListRepository;
import com.gpmrks.dslistapi.Services.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ListServiceImpl implements ListService {

    private ListRepository listRepository;

    @Autowired
    public ListServiceImpl(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ListDTO> getAllGameList() {
        List<ListOfGames> listOfGames = listRepository.findAll();
        return listOfGames.stream().map(gameListToDto -> new ListDTO(gameListToDto.getId(), gameListToDto.getName())).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public java.util.List<MinimalGameInfoDTO> searchByList(Long listId) {
        checkIfGameListExists(listId);
        List<MinimalGameInfoProjection> minimalGameInfoProjections = listRepository.searchByList(listId);
        return minimalGameInfoProjections.stream()
                .map(minimalGameInfoProjection -> new MinimalGameInfoDTO(
                        minimalGameInfoProjection.getId(),
                        minimalGameInfoProjection.getTitle(),
                        minimalGameInfoProjection.getGameYear(),
                        minimalGameInfoProjection.getImageUrl(),
                        minimalGameInfoProjection.getShortDescription()))
                .toList();
    }

    @Override
    public ListDTO getListById(Long listId) {
        ListOfGames listOfGames = checkIfGameListExists(listId);
        return new ListDTO(listOfGames.getId(), listOfGames.getName());
    }

    @Override
    public ListDTO createList(ListForm listForm) {
        ListOfGames listOfGames = new ListOfGames();
        listOfGames.setName(listForm.name());
        ListOfGames listOfGamesSaved = listRepository.save(listOfGames);
        return new ListDTO(listOfGamesSaved.getId(), listOfGamesSaved.getName());
    }

    private ListOfGames checkIfGameListExists(Long listId) {
        Optional<ListOfGames> optionalGameList = listRepository.findById(listId);
        final ListOfGames listOfGames;

        if (optionalGameList.isPresent()) {
            listOfGames = optionalGameList.get();
        } else {
            throw new ListNotFoundException(listId);
        }
        return listOfGames;
    }
}
