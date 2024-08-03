package service.implementation;

import db.DataBase;
import model.Phone;
import service.PhoneService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PhoneServiceImpl implements PhoneService {

    @Override
    public String addPhone(Phone phone) {
        try {
            DataBase.phones.add(phone);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "Success";
    }

    @Override
    public Phone getPhoneById(int phoneId) {
        return DataBase.phones.stream()
                .filter(phone -> phone.getId()==phoneId)
                .findFirst()
                .orElse(null);




    }

    @Override
    public Phone updatePhoneNameById(int phoneId, String newName) {
      Phone phone =   DataBase.phones.stream()
                .filter(p->p.getId()==phoneId)
                .findFirst()
                .orElse(null);

        assert phone != null;
        phone.setName(newName);
        return phone;
    }

    @Override
    public List<Phone> getAllPhones() {
        return DataBase.phones.stream()
                .toList();

    }

    @Override
    public List<Phone> getAllPhonesByBrand(String brand) {
        return DataBase.phones.stream()
                       .filter(phone ->
                          phone.getBrand()
                                .equalsIgnoreCase(brand))
                .toList();
    }

    @Override
    public void deletePhoneById(int phoneId) {
        boolean removed = DataBase.phones.removeIf(phone -> phone.getId() == phoneId);
        if (!removed) {
            // Можно добавить обработку случая, когда телефон не найден, например:
            System.out.println("Phone with ID " + phoneId + " not found.");
            // Или выбросить исключение, если это предпочтительнее
            // throw new NoSuchElementException("Phone with ID " + phoneId + " not found.");
        }








    }
}
