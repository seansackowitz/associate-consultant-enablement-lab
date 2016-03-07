/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstart.hibernate4.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/*The Model uses JPA Entity as well as Hibernate Validators
 * 
 */

@Entity
@XmlRootElement
@Table(name = "Member", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Member implements Serializable {
    /** Default value included to remove warning. Remove or modify at will. **/
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "hibernate_sequence")
    private Long id;

    @Version
    @Column
    private int version = 0;

    @NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
    @Column
    private String name;

    /** using hibernate4 validators **/
    @NotNull
    @NotEmpty
    @Email
    @Column
    private String email;

    @NotNull
    @Size(min = 9, max = 12)
    @Digits(fraction = 0, integer = 12)
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column
    private String address;
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private MemberInfo info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

	public MemberInfo getInfo() {
		return info;
	}

	public void setInfo(MemberInfo info) {
		this.info = info;
	}
}
