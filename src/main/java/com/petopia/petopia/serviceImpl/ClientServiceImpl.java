package com.petopia.petopia.serviceImpl;

import com.petopia.petopia.Dto.AddressResponseDTO;
import com.petopia.petopia.Dto.UserAddressRequestDTO;
import com.petopia.petopia.Dto.UserRequestDTO;
import com.petopia.petopia.Dto.UserResponseDTO;
import com.petopia.petopia.cache.UserCacheDTO;
import com.petopia.petopia.entity.Roles;
import com.petopia.petopia.entity.User;
import com.petopia.petopia.entity.UserAddress;
import com.petopia.petopia.entity.UserDetails;
import com.petopia.petopia.error.CustomValidationException;
import com.petopia.petopia.error.ErrorCode;
import com.petopia.petopia.repository.RolesRepository;
import com.petopia.petopia.repository.UserAddressRepository;
import com.petopia.petopia.repository.UserDetailsRepository;
import com.petopia.petopia.repository.UserRepository;
import com.petopia.petopia.service.ClientService;
import com.petopia.petopia.utils.PetoPiaConstants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolesRepository roleRepository;

    @Autowired
    UserAddressRepository addressRepository;


    @Transactional
    public UserResponseDTO clientRegistration(UserRequestDTO userRequestDTO) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        UserDetails existingUser = userDetailsRepository.findByUserEmail(userRequestDTO.getEmail());

        if(null != existingUser){
            responseDTO.setMessage(PetoPiaConstants.USER_ALREADY_EXIST);
            responseDTO.setStatus(false);
            return responseDTO;
        }
        LocalDateTime now = LocalDateTime.now();
        UserDetails userDetails = getUserDetails(userRequestDTO, now);

        if(!PetoPiaConstants.USER_ROLE.equals(userRequestDTO.getRoleId())){
            userDetails.setStatus(PetoPiaConstants.PENDING_STATUS);
        }else{
            userDetails.setStatus(PetoPiaConstants.VERIFIED_STATUS);
        }
        userDetailsRepository.save(userDetails);

        saveUserAddress(userRequestDTO.getAddress(),userDetails);

        // saving user details for login in userDetails
        saveUserDetailsInUser(userRequestDTO,userDetails.getId(),now, userRequestDTO.getRoleId());
        responseDTO.setUserId(userDetails.getId());
        responseDTO.setUserName(userDetails.getUserName());
        responseDTO.setMessage(PetoPiaConstants.REGISTRATION_SUCCESS_MSG);
        responseDTO.setStatus(true);
        return responseDTO;
    }

    private void saveUserAddress(UserAddressRequestDTO addresses, UserDetails user ) {

        UserAddress address = UserAddress.builder()
                .userDetails(user)
                .line1(addresses.getLine1())
                .line2(addresses.getLine2())
                .cityId(addresses.getCityId())
                .stateId(addresses.getStateId())
                .countryId(addresses.getCountryId())
                .pincode(addresses.getPincode())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isActive(true)
                .isDeleted(false)
                .build();

        addressRepository.save(address);

    }

    @Override
    public UserResponseDTO getUser(Long userId) {

        UserDetails user = userDetailsRepository.findByUserIdAndIsDeletedFalseAndIsActive(userId);
        if(null == user){
            throw new CustomValidationException(ErrorCode.CODE_2002);
        }

        UserResponseDTO responseDTO = getUserResponseDTO(user);
        responseDTO.setAddress(getUserAddress(user.getUserAddress(), userId));
        return responseDTO;
    }

    private UserResponseDTO getUserResponseDTO(UserDetails user) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setUserId(user.getId());
        responseDTO.setUserName(user.getUserName());
        responseDTO.setEmail(user.getEmail());
        return responseDTO;
    }

    private AddressResponseDTO getUserAddress(UserAddress userAddress, Long userId) {
        return addressRepository.findByUserIdAndStatus(userId);

    }

    private void saveUserDetailsInUser(UserRequestDTO userDetails, Long id, LocalDateTime now,Long roleId) {

        User user = new User();
        user.setEmail(userDetails.getEmail());
        user.setUsername(userDetails.getUserName());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        user.setUserId(id);
        user.setIsDeleted(false);
        user.setCreatedAt(now);
        Roles role = roleRepository.findByRoleId(roleId);
        user.setRole(role);
        userRepository.save(user);
    }

    private static UserDetails getUserDetails(UserRequestDTO userRequestDTO, LocalDateTime now) {
        UserDetails userDetails = new UserDetails();
        userDetails.setUserName(userRequestDTO.getUserName());
        userDetails.setEmail(userRequestDTO.getEmail());
        userDetails.setPhoneNumber(userDetails.getPhoneNumber());
        userDetails.setIsActive(true);
        userDetails.setIsDeleted(false);
        userDetails.setCreatedAt(now);
        userDetails.setUpdatedAt(now);
        userDetails.setPhoneNumber(userRequestDTO.getPhoneNumber());

        return userDetails;
    }

    @Override
    public UserResponseDTO updateUserDetails(UserRequestDTO userRequestDTO, UserCacheDTO cacheDTO) {

        UserDetails userDetails = userDetailsRepository.findByUserIdAndIsDeletedFalseAndIsActive(cacheDTO.getUserId());

        if(null == userDetails){
            throw new CustomValidationException(ErrorCode.CODE_2003);
        }

        userDetails.setPhoneNumber(userRequestDTO.getPhoneNumber());
        userDetails.setUserName(userDetails.getUserName());
        userDetails.setUpdatedAt(LocalDateTime.now());
        userDetails.setUpdatedBy(cacheDTO.getEmail());
        
        userDetailsRepository.save(userDetails);
        
        return getUserResponseDTO(userDetails);
    }

    @Override
    public UserResponseDTO updateUserAddress(UserAddressRequestDTO userRequestDTO, UserCacheDTO cacheDTO){

        UserAddress address = addressRepository.findByUserIdAndIsDeletedFalse(cacheDTO.getUserId());

        if(null == address){
            throw new CustomValidationException(ErrorCode.CODE_2003);
        }

        address.setLine1(userRequestDTO.getLine1());
        address.setLine2(userRequestDTO.getLine2());
        address.setCityId(userRequestDTO.getCityId());
        address.setPincode(userRequestDTO.getPincode());
        address.setStateId(userRequestDTO.getStateId());
        address.setCountryId(userRequestDTO.getCountryId());
        address.setUpdatedAt(LocalDateTime.now());
        addressRepository.save(address);

        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setUserId(cacheDTO.getUserId());
        responseDTO.setMessage(PetoPiaConstants.ADDRESS_UPDATED_MSG);
        responseDTO.setStatus(true);
        return responseDTO;
    }

    @Override
    public UserResponseDTO deleteUserDetails(Long userId, UserCacheDTO cacheDTO) {

        UserDetails userDetails = userDetailsRepository.findByUserIdAndIsDeletedFalseAndIsActive(cacheDTO.getUserId());

        if( null == userDetails ){
            throw new CustomValidationException(ErrorCode.CODE_2003);
        }

        userDetails.setIsDeleted(true);
        userDetails.setIsActive(false);
        userDetails.setUpdatedAt(LocalDateTime.now());
        userDetails.setUpdatedBy(cacheDTO.getEmail());
        userDetailsRepository.save(userDetails);

        UserAddress address = addressRepository.findByUserIdAndIsDeletedFalse(userId);
        if(null != address){
            address.setIsDeleted(true);
            address.setIsActive(false);
            address.setUpdatedAt(LocalDateTime.now());
            addressRepository.save(address);
        }

        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setUserId(userId);
        responseDTO.setMessage(PetoPiaConstants.USERE_DELETE_MSG);
        responseDTO.setStatus(false);
        return responseDTO;
    }
}
