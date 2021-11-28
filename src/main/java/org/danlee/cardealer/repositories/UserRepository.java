package org.danlee.cardealer.repositories;

import org.danlee.cardealer.db.MockDatabase;
import org.danlee.cardealer.entities.User;
import org.danlee.cardealer.enums.UserRoles;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository {
    private final MockDatabase mockDatabase;

    public UserRepository(MockDatabase mockDatabase) {
        this.mockDatabase = mockDatabase;
    }

    public ArrayList<User> findAll() {
       return mockDatabase.getAllUsers();
    }

    public User findBuyerById(UUID id) {
        ArrayList<User> allBuyers = findAllBuyers();

        List<User> filteredUsers = allBuyers.stream().filter(user -> user.getId().equals(id)).toList();

        if (filteredUsers.size() == 0) {
            return null;
        }

        return filteredUsers.get(0);
    }

    public User findByRoleAndId(UserRoles role, UUID id) throws Exception {
        if (role.equals(UserRoles.Buyer)) {
            return findBuyerById(id);
        }

        if (role.equals(UserRoles.Seller)) {
            return findSellerById(id);
        }

        throw new Exception("User Roles is invalid");
    }

    public User findSellerById(UUID id) {
        ArrayList<User> allBuyers = findAllBuyers();

        List<User> filteredUsers = allBuyers.stream().filter(user -> user.getId().equals(id)).toList();

        if (filteredUsers.size() == 0) {
            return null;
        }

        return filteredUsers.get(0);

    }

    public ArrayList<User> findAllBuyers() {
        return getAllByRole(UserRoles.Buyer);
    }

    public ArrayList<User> findAllSellers() {
        return getAllByRole(UserRoles.Seller);
    }

    public ArrayList<User> getAllByRole(UserRoles role) {
        return new ArrayList<>(mockDatabase.getAllUsers().stream().filter(user -> user.getRole().equals(role)).toList());

    }
}
