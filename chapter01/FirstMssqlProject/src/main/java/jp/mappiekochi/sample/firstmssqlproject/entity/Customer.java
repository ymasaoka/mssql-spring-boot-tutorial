package jp.mappiekochi.sample.firstmssqlproject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;

@Data
@Entity
@Table(schema = "SalesLT", name = "Customer")
public class Customer {
    @Id
    @Column(name = "CustomerID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column(name = "NameStyle", length = 1, nullable = false)
    private String nameStyle;

    @Column(name = "Title", length = 8)
    private String title;

    @Column(name = "FirstName", length = 100, nullable = false)
    private String firstName;

    @Column(name = "MiddleName", length = 100)
    private String middleName;

    @Column(name = "LastName", length = 100, nullable = false)
    private String lastName;

    @Column(name = "Suffix", length = 10)
    private String suffix;

    @Column(name = "CompanyName", length = 128)
    private String companyName;

    @Column(name = "SalesPerson", length = 256)
    private String salesPerson;

    @Column(name = "EmailAddress", length = 50)
    private String emailAddress;

    @Column(name = "Phone", length = 50)
    private String phone;

    @Column(name = "PasswordHash", length = 128, nullable = false)
    private String passwordHash;

    @Column(name = "PasswordSalt", length = 10, nullable = false)
    private String passwordSalt;

    @Column(name = "rowguid", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String rowguid;

    @Column(name = "ModifiedDate", nullable = false)
    private Calendar modifiedDate;
}
