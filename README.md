# Android_CotroceniNationalMuseum

Cerinte :
  Să se creeze un formular electronic inteligent cu proprietățile următoare:
- Calcularea automată cu ajutorul formulelor (de ex. calculare automată de sume, procente în cazul calcului de TVA, impozit etc).
- Generarea automată a unui ordin de plată (sub forma pdf sau txt)
- Corelarea câmpurilor
- Completare corectă
- Buton „submit”. În urma apăsării lui, datele sunt trimise către o autoritate publică. Datele și un doc XML se trimit pe server și se încarcă într-o bază de date.


App's flow :
1. Utilizatorul isi introduce datele pentru a-si rezerva un bilet.
2. Validarea campurilor in functie de tipul de data necesar; aparitia unor mesaje de eroare 
3. Calculul unor campuri pe baza unor formule (aplicarea unor reduceri elevilor si pensionarilor, calcularea totalului de plata in functie de optiuni suplimentare)
3. Realizarea unui screenshot in vederea obtinerii ticketului virtual cu extensia .png
4. Salvarea ticketului sub format pdf in memoria telefonului
5. Salvarea datelor intr-o baza de date SQLite si intr-un fisier XML.
