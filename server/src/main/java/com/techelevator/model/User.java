package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import java.util.Set;

/**
 * Model class representing an application user.
 *
 * Contains information about the user - their id, username, address information,
 * password (hashed) and authorities (user roles).
 */
public class User {

   private int id;
   private String username;
   @JsonIgnore
   private String hashedPassword;
   private String role;
   private boolean enabled = true;
   private String type;

   public User() { }

   public User(int id, String username, String hashedPassword, String role, String type) {
      this.id = id;
      this.username = username;
      this.hashedPassword = hashedPassword;
      this.role = role;
      this.type = type;
   }

   public User(int id, String username, String hashedPassword, String role, boolean enabled, String type) {
      this.id = id;
      this.username = username;
      this.hashedPassword = hashedPassword;
      this.role = role;
      this.enabled = enabled;
      this.type = type;
   }
   public User(String username, String hashedPassword, String role, String type) {
      this(0, username, hashedPassword, role, type);
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getHashedPassword() {
      return hashedPassword;
   }

   public void setHashedPassword(String hashedPassword) {
      this.hashedPassword = hashedPassword;
   }

   public String getRole() {
      return role;
   }

   public void setRole(String role) {
      this.role = role != null && !role.startsWith("ROLE_")
              ? "ROLE_" + role.toUpperCase()
              : role;
   }

   public boolean isEnabled() {
      return enabled;
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      return id == user.id &&
              Objects.equals(username, user.username) &&
              Objects.equals(hashedPassword, user.hashedPassword) &&
              Objects.equals(role, user.role) &&
              Objects.equals(enabled, user.enabled) &&
              Objects.equals(type, user.type);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, username, hashedPassword, role, enabled, type);
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", username='" + username + '\'' +
              ", hashedPassword='" + hashedPassword + '\'' +
              ", role='" + role + '\'' +
              ", enabled='" + enabled + '\'' +
              ", type='" + type + '\'' +
              '}';
   }
}
