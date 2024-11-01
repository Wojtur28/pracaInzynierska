package org.example.pracainzynierska.core.entities.genre;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreEntity findGenre(String name) {
        Optional<GenreEntity> genreOpt = genreRepository.findByName(name);
        return genreOpt.orElseThrow(() ->
                new RuntimeException());
        }

}
