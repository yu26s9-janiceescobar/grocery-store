package org.yearup.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.yearup.exception.ResourceNotFoundException;
import org.yearup.models.Profile;
import org.yearup.repository.ProfileRepository;

@Service
public class ProfileService
{
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository)
    {
        this.profileRepository = profileRepository;
    }

    public Profile create(Profile profile)
    {
        return profileRepository.save(profile);
    }
    public Profile getProfile(Long userId){
        return profileRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Profile Not Found: " + userId));
    }
    @Transactional
    public Profile updateProfile(Profile profile, Long userId){
        return profileRepository.findById(userId).map(existing -> {
            existing.setAddress(profile.getAddress());
            existing.setCity(profile.getCity());
            existing.setEmail(profile.getEmail());
            existing.setPhone(profile.getPhone());
            existing.setState(profile.getState());
            existing.setZip(profile.getZip());
            existing.setFirstName(profile.getFirstName());
            existing.setLastName(profile.getLastName());
            return profileRepository.save(existing);
        }).orElseThrow(()-> new ResourceNotFoundException("Profile Not Found."));
    }

}
