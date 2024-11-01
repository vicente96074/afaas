package com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.adapters;

import com.kojstarinnovations.afaas.commons.data.dto.RolDTO;
import com.kojstarinnovations.afaas.commons.emuns.RolName;
import com.kojstarinnovations.afaas.us.domain.opextends.RolOP;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.entity.Rol;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.pmimpl.RolPM;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * RolPA
 *
 * @author BalamKiche
 */
@Component
public class RolPA implements RolOP {

    /**
     * existsById method
     *
     * @param id id
     * @return boolean
     */
    @Override
    public boolean existsById(int id) {
        return rolRepository.existsById(id);
    }

    /**
     * existsByRolName method
     *
     * @param rolName rolName
     * @return boolean
     */
    @Override
    public boolean existsByRolName(RolName rolName) {
        return rolRepository.existsByRolName(rolName);
    }

    /**
     * This method saves a model
     *
     * @param modelDto the model to be saved
     * @return the saved model
     */
    @Override
    public RolDTO save(RolDTO modelDto) {
        return persistenceMapper.entityToDto(
                rolRepository.save(
                        persistenceMapper.dtoToEntity(modelDto)
                )
        );
    }

    /**
     * This method gets a model by id
     *
     * @param integer the id of the model to be fetched
     * @return the fetched model
     */
    @Override
    public Optional<RolDTO> getById(Integer integer) {
        return rolRepository.findById(integer)
                .map(persistenceMapper::entityToDto);
    }

    /**
     * Method to get a page of modelDto
     *
     * @param pageable the pageable object
     * @return Page<DTO>
     */
    @Override
    public Page<RolDTO> getPage(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Method to get all modelDto
     *
     * @return List<DTO>
     */
    @Override
    public List<RolDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * This method updates a model
     *
     * @param modelDto the model to be updated
     * @param integer the id of the model to be updated
     * @return the updated model
     */
    @Override
    public RolDTO updateById(RolDTO modelDto, Integer integer) {
        Rol rol = persistenceMapper.dtoToEntity(modelDto);
        rol.setId(integer);

        return persistenceMapper.entityToDto(rolRepository.save(rol));
    }

    /**
     * This method gets a model by rol name
     *
     * @param rolName the rol name of the model to be fetched
     * @return the fetched model
     */
    @Override
    public Optional<RolDTO> getByRolName(RolName rolName) {
        return rolRepository.findByRolName(rolName)
                .map(persistenceMapper::entityToDto);
    }

    /**
     * This method deletes a model by id
     *
     * @param integer the id of the model to be deleted
     */
    @Override
    public void deleteById(Integer integer) {
        rolRepository.deleteById(integer);
    }

    /**
     * Method to check if a modelDto exists by id
     *
     * @param integer the id of the modelDto to be checked
     * @return true if the modelDto exists, false otherwise
     */
    @Override
    public boolean existsById(Integer integer) {
        return rolRepository.existsById(integer);
    }

    @Autowired
    public RolPA(
            RolRepository rolRepository,
            RolPM persistenceMapper
    ) {
        this.rolRepository = rolRepository;
        this.persistenceMapper = persistenceMapper;
    }

    private final RolRepository rolRepository;
    private final RolPM persistenceMapper;
}