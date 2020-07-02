# Telia internship homework Dictionary

In this project, I used Vue.js on the front end and Node.js on the backend (to run the application you will have to download them if you
don’t have them on your computer). Since we were asked not to complicate the architecture of the project, I decided that the database
would be two JSON files, which in turn are in the backend folder.

And so, this application allows the user to search for translation of words from English to Estonian and vice versa. In addition to the 
translation, the user receives words that are similar in writing ***not in meaning***. For this I used Levenshtein distance algorithm where
the maximum distance between words is 3. But in addition to translation, the user has the opportunity to add words to the dictionary in both languages.
If for example a word has been added from English to Estonian, then it will also be added to the Estonian-English dictionary.

For design, I used a ready-made layout material design component framework **Vuetify**. But the beauty is that the application 
is responsive, which means that it can be used on the phone, while the aesthetics do not disappear.

**LET'S LOOK AT THE APPLICATION!**

Firstly, you need to go to the backend folder and write in the command console

```
npm install
npm start
```
this way you start the backend server

After that, go to the frontend folder and write in the command console

```
npm install
npm run serve
```

DONE!!! Now go to the http://localhost:8080/ and check it out, it should work.

I also wrote two separate tests. The first checks the front-end and the second checks the entire system. 
To run the first test you need to go to the frontend folder and write in the command console

```
npm test
```
This test verifies that the components are correct.

Another tests were created in Java using Selenium. **For running then You should have _Google Chrome Version 38_ and You should change path of chromedriver.exe in \webapptesting\src\test\java both files.**
