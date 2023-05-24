# Chapter 1
SQL Server からデータを取得して画面に表示してみよう

## この Chapter で学ぶこと

- Spring Boot アプリで SQL Server に接続する
- JPA / Hibernate を使用して SQL Server からデータを取得する
- 取得したデータをブラウザで表示する

# 1. 開発環境を準備する

まず最初に、このチュートリアルを進めるにあたり必要となる開発環境を構築します。  
以下の環境を用意しましょう。  

- JDK 17 (or higher)
- 統合開発環境 (IDE)
- SQL Server
- SQL Server Management Studio または Azure Data Studio
- AdventureWorks データベース

## JDK 17

Java アプリを動かすため、Java Development Kit (JDK) と呼ばれるツールをインストールします。  
JDK には OpenJDK をはじめ、さまざまな種類やインストール方法があります。好きなものを利用してもらって問題ないです。  
バージョンは JDK 17 を選択してください。

### Java 初心者向けの JDK インストール解説

Java 初心者においては、ここでは、Oracle JDK のインストールを推奨します。理由は、環境構築が一番簡単だからです。  

https://www.oracle.com/java/technologies/downloads/

ダウンロードサイトの中央付近に OS（Linux、macOS、Windows）を選択するリンクがあります。自分の PC に対応する OS を選択してください。  
**Product/file description** 列で `*** Installer` や `*** Package` が付いているものをダウンロードしましょう。  
macOS 環境においては、Intel CPU 環境の場合は x64 DMG Installer、Apple M1/M2 CPU 環境の場合は Arm 64 DMG Installer を選択するようにしてください。

インストーラーをダウンロードしたら、インストーラーを実行し、画面に沿って JDK 17 のインストールを行なってください。  

## 統合開発環境 (IDE)

Java アプリを開発するための IDE (Integrated Development Environment) をインストールします。  
Java アプリを開発するための IDE も JDK 同様、Eclipse や Visual Studio Code、IntelliJ など、さまざまな種類が存在しますので、好きなものを利用してもらって構いません。  

ただし、このチュートリアルでは IntelliJ IDEA を使用する前提で話を進めます。  

### Java 初心者向けの IntelliJ IDEA インストール解説

IntelliJ IDEA とは、海外では古くから愛用され、世界 No.1 のシェアを誇る Java 用の IDE です。チェコのプラハに本社がある JetBrains 社により提供されています。  
2020 年には公式プラグインで日本語化ができるようになったことから、それまで一般的だった Eclipse からの切り替えも進んでいます。  

IntelliJ IDEA には、無償版の `Community Edition` と、有償版の `Ultimate` が存在します。本チュートリアルを進めるにあたっては、Community Edition のインストールで問題ありません。  
IntelliJ IDEA のインストールには、**JetBrains Toolbox App** を使用するのが便利です。これは、IDE の自動バージョンアップやバージョン切り替えを支援してくれる公式ツールになります。  

https://www.jetbrains.com/toolbox-app/

JetBrains Toolbox App をインストールしたら、アプリを起動し、画面に表示される IDE の一覧から `IntelliJ IDEA Community Edition` を見つけ、インストールを行なってください。  
インストールが完了したら、IntelliJ IDEA Community Edition を起動します。初回起動時、利用規約の確認などが入る場合があります。  
起動したら、日本語プラグインを一緒に導入しておきましょう。  
[Welcome to IntelliJ IDEA] というホーム画面が表示されたら、[Plugins] メニューを選択し、[Marketplace] タブ内の検索ボックスに `Japanese` と入力します。そうすると **Japanese Language Pack / 日本語言語パック** というプラグインが表示されるので、インストールを行なってください。  
インストール完了後、IntelliJ IDEA を再起動すると、日本語化が完了します。  

## SQL Server

このチュートリアルで使用する SQL Server をインストールします。  
SQL Server にも、さまざまなバージョンやエディションやありますので、すでに所有の環境があればそれを利用してもらって構いません。

ただし、このチュートリアルでは、SQL Server 2022 Developer Edition を使用する前提で話を進めます。  

### SQL Server 初心者向けの SQL Server インストール解説

SQL Server には、運用ワークロード向けとして、Express (無償) / Standard (有償) / Enterprise (有償) の 3 つのエディションと、開発環境向けの Developer (無償) というエディションの計 4 つが存在しています。このチュートリアルでは Developer エディションを利用しますが、実際の運用ワークロードでは適切なエディションを利用してください。  
Enterprise エディション、および Developer エディションは SQL Server の全機能が利用できますが、Express や Standard エディションには一部、機能制約があります。  

Windows 環境に SQL Server をインストールする際は、公式サイトからインストーラーをダウンロードします。  

https://www.microsoft.com/ja-jp/sql-server/sql-server-downloads

[または、無料の専用エディションをダウンロード] の欄に表示されている `Developer` の欄から、インストーラーをダウンロードしてください。  

macOS や 環境に SQL Server をインストールする際は、Docker を使用する必要があります。詳細は以下のリポジトリおよびブログを参照してください。  

https://github.com/ymasaoka/docker-mssql
https://qiita.com/ymasaoka/items/7d7bc2de76dbee559a56#sql-server-%E3%81%AE%E3%82%A4%E3%83%B3%E3%82%B9%E3%83%88%E3%83%BC%E3%83%AB

RHEL や SUSE、Ubuntu などの Linux 環境に SQL Server をインストールする場合は、macOS 同様に Docker の利用に加え、各 OS のソースリポジトリに対応したインストールも可能です。詳細は以下を参照してください。  

https://learn.microsoft.com/ja-jp/sql/linux/sql-server-linux-setup?view=sql-server-ver16

## SQL Server Management Studio または Azure Data Studio

SQL Server を GUI で簡単に操作できるようにするため、SQL Server Management Studio (SSMS) または Azure Data Studio (ADS) をインストールします。  
Windows 環境では、SSMS または ADS 好きな方をインストールしてください。macOS 環境では SSMS は利用できないため、ADS をインストールしてください。  

SSMS、または ADS のインストールについては、以下のサイトを参照してください。  

https://learn.microsoft.com/ja-jp/sql/ssms/download-sql-server-management-studio-ssms?view=sql-server-ver16

https://learn.microsoft.com/ja-jp/sql/azure-data-studio/download-azure-data-studio?view=sql-server-ver16&tabs=redhat-install%2Credhat-uninstall

## AdventureWorks データベース

このチュートリアルでは、Microsoft 社が公開しているサンプルデータベース **AdventureWorksLT** を使用します。  
以下のリポジトリから AdventureWorks データベースをリストアするためのバックアップファイル (.bak) をダウンロードし、構築済みの SQL Server インスタンス上でリストア (復元) を行なってください。  

https://github.com/Microsoft/sql-server-samples/releases/tag/adventureworks

なお、このチュートリアルでは、`AdventureWorksLT2022.bak` ファイルを使用する前提で話を進めます。  
リストア手順については、以下の公式サイトを参照してください。  

https://learn.microsoft.com/ja-jp/sql/samples/adventureworks-install-configure?view=sql-server-ver16&tabs=ssms#restore-to-sql-server


# 2. Spring Initializr で最初の Spring Boot アプリを作成する

このチュートリアルでは、**Spring Boot** を使用した Web アプリを構築します。  
Spring Boot についての解説はここでは行いませんが、簡単に言うと、Java アプリケーション開発を行う際において、小規模な Web システムから大規模な業務システムまで幅広くサポートできる Spring Framework を簡単に利用できるものです。  

## Spring Initializr

Spring Initializr は、Spring Boot アプリケーションの雛形を作成してくれるサービスで、Spring プロジェクトが公式に無料で提供しているものです。  

https://start.spring.io/

チュートリアルを進めるにあたり、以下の内容で Spring Boot アプリケーションを作成してください。  

| 項目 | 設定値 | 説明 |
| :-- | :-- | :-- |
| Project | Maven | |
| Language | Java | |
| Spring Boot | デフォルト値 (SNAPSHOT や M1 などがついていないもので最新のもの) | |
| Group | jp.mappiekochi.sample | |
| Artifact | firstmssqlproject | |
| Name | FirstMssqlProject | |
| Description | Demo project for Spring Boot and SQL Server | |
| Package Name | jp.mappiekochi.sample.firstmssqlproject | |
| Packaging | Jar | |
| Java | 17 | |
| Dependencies | Spring Web<br/>Spring Data JPA<br/>MS SQL Server Driver<br/>Thymeleaf<br/>Lombok | |

設定ができたら、`GENERATE` ボタンを選択し、ZIP ファイルをダウンロードします。  
ダウンロードが完了したら、それを適当なフォルダに解凍してください。  


# 3. SQL Server アクセスに必要となるライブラリの設定を行う

IntelliJ IDEA で解凍したフォルダ (プロジェクト) を開きます。最初にプロジェクトを開く際には、少し時間がかかります。  
プロジェクトを開いたら、まずは JDK と実行構成の設定を行います。  

## JDK の設定

[ファイル (File)] -> [プロジェクト構造... (Project Structure...)] を選択し、[プロジェクト構造ダイアログ] を開きます。  
[プロジェクト構造] -> [プロジェクト] を選択し、SDK 欄で使用する JDK を選択します。※このチュートリアルでは `Oracle Open JDK 17` を選択した前提で進めます。  
選択後、OK を選択し、設定を反映してください。  

## 実行/デバック構成の設定

画面右上にある [構成の追加 (Add Configuration) ...] を選択します。  
左上の [+] アイコンを選択し、[新規構成の追加] -> [アプリケーション] を選択します。  
[名前 (N)] 欄に `Tomcat` を入力し、[ビルドと実行] 欄に Java 17 を指定します。  
メインクラスの選択は `jp.mappiekochi.sample.firstmssqlproject.FirstMssqlProjectApplication` を選択してください。  

## JDBC Driver for SQL Server の設定

先ほど Spring Initializr で追加した `MS SQL Server Driver` は、Microsoft 社が公開している SQL Server の接続ドライバになります。  

https://learn.microsoft.com/ja-jp/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver16

プロジェクトに追加されていることを実際に確認するために、`pom.xml` ファイルを開きます。  
以下の内容が記載されていることを確認しましょう。  

```xml:pom.xml
<dependency>
    <groupId>com.microsoft.sqlserver</groupId>
	<artifactId>mssql-jdbc</artifactId>
	<scope>runtime</scope>
</dependency>
```

追加されていることが確認されたら、`application.properties` ファイルを開きましょう。  
Spring Boot アプリケーションで JDBC Driver for SQL Server を使用する場合、SQL Server への接続情報は application.properties ファイルに以下の情報を記載します。  

| 名前 | 入力値 | 備考 |
| :-- | :-- | :-- |
| spring.datasource.driver-class-name | com.microsoft.sqlserver.jdbc.SQLServerDriver | |
| spring.datasource.url | <SQL Server への接続文字列> | |
| spring.datasource.username | <接続に使用する SQL Server アカウント名> | |
| spring.datasource.password | <接続に使用する SQL Server アカウントのパスワード> | |

入力例としては、以下のようになります。  

```:application.properties
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.datasource.url=jdbc:sqlserver://localhost:1433;Database=AdventureWorksLT2022;trustServerCertificate=true;
spring.datasource.username=sa
spring.datasource.password=password
```


# 4. SQL Server にアクセスし、データを取得する

では、ここからは実際に SQL Server から取得するコードを書いていきます。  
このチュートリアルでは、**MVC モデル**をベースにアプリケーションを作成します。  

## エンティティの作成

データベースアクセスに JPA / Hibernate を使用する場合、データベースのデータに相当する部分は **エンティティ** と呼ばれるクラスとして定義します。  
つまり、1 データベースレコードが 1 エンティティのクラス、のイメージです。  

Spring Boot アプリケーションにおけるエンティティクラスは、基本的に以下のようになっています。  

```java
@Entity
public class クラス名 {
    フィールド名; // テーブルのカラムに相当
    // 以下、必要な分だけフィールドを定義
}
```

今回は、AdventureWorksLT2022 データベースの `SalesLT.Customer` テーブルに紐づくエンティティクラスを作成します。  
IntelliJ IDEA で `jp.mappiekochi.sample.firstmssqlproject` ディレクトリを**右クリック**し、[新規] -> [Java クラス] を選択します。  
新規 Java クラスのダイアログで、以下を入力し、Enter を押下しましょう。  

- 名前 : `entity.Customer`
- クラス種別: `クラス`

そうすると、`jp.mappiekochi.sample.firstmssqlproject` ディレクトリ以下に新しく `entity` ディレクトリが作成され、ディレクトリの中に `Customer` クラスが作成されています。
Customer クラスが作成されたのを確認したら、クラスの中身を書いていきます。  
以下のように、クラスの中を記載してみましょう。

```java:Customer.java
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
```


## リポジトリの作成

```java:CustomerRepository.java
package jp.mappiekochi.sample.firstmssqlproject.repository;

import jp.mappiekochi.sample.firstmssqlproject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
```

## コントローラーの作成

```java:HomeController.java
package jp.mappiekochi.sample.firstmssqlproject.controller;

import jp.mappiekochi.sample.firstmssqlproject.entity.Customer;
import jp.mappiekochi.sample.firstmssqlproject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private CustomerRepository repository;

    @RequestMapping("/")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("title", "[AdventureWorks2022].[SalesLT].[Customer]");
        mav.addObject("msg", "テーブルデータを取得しました。");
        Iterable<Customer> data = repository.findAll();
        mav.addObject("data", data);
        return mav;
    }
}
```

## index.html の作成

```html:index.html
<!DOCTYPE html>
<head>
  <title>top page</title>
  <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container">
  <h1 class="display-4 mb-4" th:text="${title}"></h1>
  <p th:text="${msg}"></p>

  <table class="table">
    <thead>
      <tr>
        <th>CustomerID</th>
        <th>NameStyle</th>
        <th>Title</th>
        <th>FirstName</th>
        <th>MiddleName</th>
        <th>LastName</th>
        <th>Suffix</th>
        <th>CompanyName</th>
        <th>SalesPerson</th>
        <th>EmailAddress</th>
        <th>Phone</th>
        <th>PasswordHash</th>
        <th>PasswordSalt</th>
        <th>rowguid</th>
        <th>ModifiedDate</th>
      </tr>
    </thead>
    <tbody>
      <tr th:each="item : ${data}">
        <td th:text="${item.customerId}"></td>
        <td th:text="${item.nameStyle}"></td>
        <td th:text="${item.title}"></td>
        <td th:text="${item.firstName}"></td>
        <td th:text="${item.middleName}"></td>
        <td th:text="${item.lastName}"></td>
        <td th:text="${item.suffix}"></td>
        <td th:text="${item.companyName}"></td>
        <td th:text="${item.salesPerson}"></td>
        <td th:text="${item.emailAddress}"></td>
        <td th:text="${item.phone}"></td>
        <td th:text="${item.passwordHash}"></td>
        <td th:text="${item.passwordSalt}"></td>
        <td th:text="${item.rowguid}"></td>
        <td th:text="${item.modifiedDate.getTime()}"></td>
      </tr>
    </tbody>
  </table>
</body>
</html>
```

## アプリケーションの実行

