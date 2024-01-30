package ma.ac.emi.bricool.service;


import ma.ac.emi.bricool.DTO.user_management.AppUserDTO;
import ma.ac.emi.bricool.entities.AppUser;
import ma.ac.emi.bricool.repositories.AppUserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ma.ac.emi.bricool.util.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(final AppUserRepository appUserRepository,
                          final PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<AppUserDTO> findAll() {
        final List<AppUser> appUsers = appUserRepository.findAll(Sort.by("id"));
        return appUsers.stream()
                .map((appUser) -> mapToDTO(appUser, new AppUserDTO()))
                .collect(Collectors.toList());
    }


    public AppUserDTO get(final Long id) {
        return appUserRepository.findById(id)
                .map(appUser -> mapToDTO(appUser, new AppUserDTO()))
                .orElseThrow(() -> new NotFoundException());
    }


    public AppUserDTO getByEmail(String email) {
        AppUser user = appUserRepository.findByEmailIgnoreCase(email);
        if (user == null){
            throw new NotFoundException();
        }
        return mapToDTO(user, new AppUserDTO());
    }



    public void update(final Long id, final AppUserDTO appUserDTO) {
        final AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(appUserDTO, appUser);
        appUserRepository.save(appUser);
    }


    public void delete(final Long id) {
        appUserRepository.deleteById(id);
    }

    private AppUserDTO mapToDTO(final AppUser appUser, final AppUserDTO appUserDTO) {
        appUserDTO.setId(appUser.getId());
        appUserDTO.setFirstName(appUser.getFirstName());
        appUserDTO.setLastName(appUser.getLastName());
        appUserDTO.setEmail(appUser.getEmail());
        appUserDTO.setPhoneNumber(appUser.getPhoneNumber());
        appUserDTO.setRole(appUser.getRole());
        return appUserDTO;
    }

    private AppUser mapToEntity(final AppUserDTO appUserDTO, final AppUser appUser) {
        appUser.setFirstName(appUserDTO.getFirstName());
        appUser.setLastName(appUserDTO.getLastName());
        appUser.setEmail(appUserDTO.getEmail());
        appUser.setPhoneNumber(appUserDTO.getPhoneNumber());
        appUser.setPassword(passwordEncoder.encode(appUserDTO.getPassword()));
        return appUser;
    }

    /*@Transactional
    @Override
    public String getReferencedWarning(final Long id) {
        final AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        if (!appUser.getRoles().isEmpty()) {
            return WebUtils.getMessage("appUser.role.oneToMany.referenced", appUser.getRoles().iterator().next().getId());
        }
        return null;
    }*/

}
