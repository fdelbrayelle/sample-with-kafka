package tech.ippon.generated.service;

import tech.ippon.generated.domain.Foo;
import tech.ippon.generated.repository.FooRepository;
import tech.ippon.generated.service.dto.FooDTO;
import tech.ippon.generated.service.mapper.FooMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Foo}.
 */
@Service
@Transactional
public class FooService {

    private final Logger log = LoggerFactory.getLogger(FooService.class);

    private final FooRepository fooRepository;

    private final FooMapper fooMapper;

    public FooService(FooRepository fooRepository, FooMapper fooMapper) {
        this.fooRepository = fooRepository;
        this.fooMapper = fooMapper;
    }

    /**
     * Save a foo.
     *
     * @param fooDTO the entity to save.
     * @return the persisted entity.
     */
    public FooDTO save(FooDTO fooDTO) {
        log.debug("Request to save Foo : {}", fooDTO);
        Foo foo = fooMapper.toEntity(fooDTO);
        foo = fooRepository.save(foo);
        return fooMapper.toDto(foo);
    }

    /**
     * Get all the foos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FooDTO> findAll() {
        log.debug("Request to get all Foos");
        return fooRepository.findAll().stream()
            .map(fooMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one foo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FooDTO> findOne(Long id) {
        log.debug("Request to get Foo : {}", id);
        return fooRepository.findById(id)
            .map(fooMapper::toDto);
    }

    /**
     * Delete the foo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Foo : {}", id);
        fooRepository.deleteById(id);
    }
}
