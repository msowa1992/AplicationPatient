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


