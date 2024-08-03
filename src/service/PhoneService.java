package service;

import model.Phone;

import java.util.List;

public interface PhoneService {
    String addPhone(Phone phone);


    Phone getPhoneById(int phoneId);

    // with stream

    Phone updatePhoneNameById(int phoneId, String newName);

    // with stream

    List<Phone> getAllPhones();

    //with stream

    List<Phone> getAllPhonesByBrand(String brand);

    void deletePhoneById(int phoneId);
}
