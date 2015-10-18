package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.*;
import java.util.UUID;
import java.util.ArrayList;

import com.util.security.Crypto;
import com.entity.Course;
import com.entity.Role;

/**
 * Data transfer object for Users_Courses
 * Based on table
 * CREATE TABLE Users_Courses(
 *      userId VARCHAR(36) NOT NULL,
 *      courseId VARCHAR(36) NOT NULL,
 *      FOREIGN KEY(userId) REFERENCES Users(userId) ON UPDATE CASCADE ON DELETE CASCADE,
 *      FOREIGN KEY(courseId) REFERENCES Courses(courseId) ON UPDATE CASCADE ON DELETE CASCADE,
 *      PRIMARY KEY (userId, courseId)
 * );
 */

 @Entity
 @Table(name="UsersCourses")
 public class UserCourse implements Serializable {

 }
