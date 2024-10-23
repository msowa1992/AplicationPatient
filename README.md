<<<<<<< HEAD
# AplicationPatient

## Opis projektu

**AplicationPatient** to aplikacja wspomagająca zarządzanie danymi pacjentów. Umożliwia rejestrację, edycję, oraz przeglądanie informacji o pacjentach, co może być użyteczne w placówkach medycznych lub systemach zdrowotnych. Aplikacja została zaprojektowana z myślą o użytkownikach, którzy potrzebują łatwego dostępu do danych pacjentów oraz ich zarządzania.

## Funkcje

- Dodawanie, edytowanie oraz usuwanie danych pacjentów
- Przeglądanie pełnej listy pacjentów
- Wyszukiwanie pacjentów po różnych kryteriach
- Obsługa historii wizyt pacjenta

## Wymagania systemowe

- **Język programowania**: Java
- **Baza danych**: MySQL (lub inna zgodna)
- **Inne zależności**: Spring Boot, Hibernate

## Instalacja i uruchomienie

Aby uruchomic projekt lokalnie, postępuj zgodnie z poniższymi krokami:

## 1.Klonowanie repozytorium

Sklonuj repozytorium do swojego środowiska lokalnego:
   ```bash
   git clone https://github.com/msowa1992/AplicationPatient.git
   cd AplicationPatient
```

## 2.Konfiguracja bazy danych

Upewnij się, że baza danych MySQL jest uruchomiona. Utwórz nową bazę danych oraz dostosuj plik `application.properties`, aby zawierał dane dostępowe do bazy danych:

```properties
CREATE DATABSE AplicationPatient

```
## 3. Budowanie i uruchomienie aplikacji
Aby zbudować i uruchomić projekt, wykonaj następujące polecenia z wiersza poleceń pamiętając, że musisz być w katalogu AplicationPatient:
   ```bash
mvn clean install
```
pamiętaj że należy podać `username` i `password` takie jakie masz dla konkretnej bazy danych
   ```bash
DATABASE_URL=jdbc:mysql://localhost:3306/AplicationPatient \
DATABASE_DRIVER=com.mysql.cj.jdbc.Driver \
DATABASE_USERNAME=root \
DATABASE_PASSWORD= \
JPA_DIALECT=org.hibernate.dialect.MySQLDialect \
mvn spring-boot:run

```


## Wygląd aplikacji - logowanie jako administrator
1. Dostęp do listy użytkowników
2. Edycja użytkowników
3. Zmiana hasła dla użytkownika - jedynie od strony administratora
4. Zmiana roli dla istniejących użytkowników aplikacji
5. Dostęp do pacjentów
6. Edycja pacjentów
## Wygląd aplikacji - logowanie jako menager lub worker
1. Dostęp do pacjentów
2. Edycja pacjentów

Aby zmienic rolę na `administrator` należy wejść do bazy np.w phpMyadmin w polu Role zmienic z Client na Admin wtedy można mieć dostęp do wszystkich czynności jako administrator.
=======
Głównymi technologiami użytymi w kodzie są Spring MVC, Spring Security,
Thymeleaf, Java Bean Validation, Lombok, oraz Bootstrap. Każda z tych technologii
wspiera określoną funkcjonalność, od zabezpieczeń, przez walidację danych, po
dynamiczne generowanie interfejsu użytkownika.
Wymagana baza danych: MySQL
 Baza o konkretnej nazwie musi już być utworzona. By to zrobić trzeba np korzystając z phpMyAdmin (http://localhost/phpmyadmin/) utorzyć baze o nawie takiej
jak : PatientApplication 
po utworzeniu bazy importować plik PatientApplication.sql -> znajduje się na githubie


Wygląd aplikacji - logowanie jako administrator
1. Dostęp do listy użytkowników
2. Edycja użytkowników 
3. Zmiana hasła dla użytkownika - jedynie od strony administratora
4. Zmiana roli dla istniejących użytkowników aplikacji
5. Dostęp do pacjentów
6. Edycja pacjentów 
Wygląd aplikacji - logowanie jako menager lub worker
1. Dostęp do pacjentów
2. Edycja pacjentów

Aby zmienic rolę na administractora można zarejestrować sie swoimi danymi i w phpMyadmin w polu Role zmienic z Client na Admin wtedy można mieć dostęp do wszystkich czynności jako administrator


>>>>>>> origin/main
