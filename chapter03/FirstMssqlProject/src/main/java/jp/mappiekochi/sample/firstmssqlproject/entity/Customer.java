package jp.mappiekochi.sample.firstmssqlproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jp.mappiekochi.sample.firstmssqlproject.annotation.Phone;
import lombok.Data;

import java.util.Calendar;

@Data
@Entity
@Table(schema = "SalesLT", name = "Customer")
public class Customer {
    @Id
    @Column(name = "CustomerID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int customerId;

    @Column(name = "NameStyle", length = 1, nullable = false)
    @NotBlank
    @Size(max = 1)
    private String nameStyle;

    @Column(name = "Title", length = 8)
    @Size(max = 8)
    private String title;

    @Column(name = "FirstName", length = 100, nullable = false)
    @NotBlank(message = "名前を入力してください")
    @Size(max = 100)
    private String firstName;

    @Column(name = "MiddleName", length = 100)
    @Size(max = 100)
    private String middleName;

    @Column(name = "LastName", length = 100, nullable = false)
    @NotBlank(message = "苗字を入力してください")
    @Size(max = 100)
    private String lastName;

    @Column(name = "Suffix", length = 10)
    @Size(max = 10)
    private String suffix;

    @Column(name = "CompanyName", length = 128)
    @Size(max = 128)
    private String companyName;

    @Column(name = "SalesPerson", length = 256)
    @Size(max = 256)
    private String salesPerson;

    @Column(name = "EmailAddress", length = 50)
    @Email()
    @Size(max = 50)
    private String emailAddress;

    @Column(name = "Phone", length = 50, nullable = true)
//    @Pattern(regexp = "^0([0-9]-[0-9]{4}|[0-9]{2}-[0-9]{3}|[0-9]{3}-[0-9]{2}|[0-9]{4}-[0-9])-[0-9]{4}$", message = "電話番号はハイフン (-) 込みで入力してください")
    @Phone(message = "電話番号はハイフン (-) 込みで入力してください")
//    @Phone(onlyNumber = true, message = "電話番号は半角数字のみで入力してください")
    @Size(max = 50)
    private String phone;

    @Column(name = "PasswordHash", length = 128, nullable = false)
    @NotBlank
    @Size(max = 128)
    private String passwordHash;

    @NotBlank
    @Size(max = 10)
    @Column(name = "PasswordSalt", length = 10, nullable = false)
    private String passwordSalt;

    @Column(name = "rowguid", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    @NotBlank
    private String rowguid;

    @Column(name = "ModifiedDate", nullable = false)
    @Future
    @NotNull
    private Calendar modifiedDate;
}
