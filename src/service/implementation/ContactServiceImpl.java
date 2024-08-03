package service.implementation;

import db.DataBase;
import model.Contact;
import model.Phone;
import service.ContactService;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ContactServiceImpl implements ContactService {
    @Override
    public String addContactToPhone(int phoneId, Contact contact) {
        Phone phone = DataBase.phones.stream()
                .filter(p -> p.getId() == phoneId)
                .findFirst()
                .orElse(null);

        if (phone == null) {
            return "Phone with ID " + phoneId + " not found.";
        }

        phone.getContacts().add(contact);
        return "Contact added successfully.";
    }

    @Override
    public Contact findContactByName(int phoneId, String contactName) {
        Phone found = DataBase.phones.stream()
                .filter(phone -> phone.getId() == phoneId)
                .findFirst()
                .orElseThrow(
                        () -> new NoSuchElementException("Phone with ID " + phoneId + " not found."));

        return found.getContacts()
                .stream()
                .filter(contact -> contact.getName().equalsIgnoreCase(contactName))
                .findFirst().orElse(null);

    }

    @Override
    public Contact findContactByPhoneNumber(int phoneId, String phoneNumber) {
        Phone foundPhone = DataBase.phones.stream()
                .filter(phone -> phone.getId() == phoneId)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Phone with ID " + phoneId + " not found."));
        return foundPhone.getContacts().stream()
                .filter(contact -> contact.getPhoneNumber().equalsIgnoreCase(phoneNumber))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("Contact with phone number %s not found", phoneNumber)));


    }

    @Override
    public List<Contact> sortContactsByName(int phoneId) {
        Phone foundPhone = DataBase.phones
                .stream()
                .filter(phone -> phone.getId() == phoneId)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("Phone with id %d not found", phoneId)));
        return foundPhone.getContacts().stream()
                .sorted(Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteContactByNameFromPhone(int phoneId, String contactName) {
        Phone found = DataBase.phones.stream()
                .filter(p -> p.getId() == phoneId)
                .findFirst()
                .orElseThrow(RuntimeException::new);

        boolean removed = found.getContacts().removeIf(contact -> contact.getName().equalsIgnoreCase(contactName));
        if (!removed) {
            System.out.println("Contact with name " + contactName + " not found.");
        }
    }
}
