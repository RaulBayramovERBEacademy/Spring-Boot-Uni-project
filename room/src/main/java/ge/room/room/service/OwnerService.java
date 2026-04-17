package ge.room.room.service;

import ge.room.room.model.Owner;
import ge.room.room.repository.OwnerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    public Optional<Owner> findById(Long id) {
        return ownerRepository.findById(id);
    }

    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }
}
