//Decided to install ready-made solution
//although I myself know this algorithm and
// can write it (iteratively or recursively)
const levenshtein = require('js-levenshtein');

const translateRoutes = (app, fs) => {
    
    const englishPath = "./data/englishToEstonia.json";
    const estoniaPath = "./data/estoniaToEnglish.json";

    //Created method to read in data
    const readFile = (callback, returnJson = false, filePath, encoding = 'utf8') => {
        fs.readFile(filePath, encoding, (err, data) => {
            if (err) {
                throw err;
            }
            callback(returnJson ? JSON.parse(data) : data);
        });
    };

    //Created method to write data into JSON file
    const writeFile = (fileData, callback, filePath, encoding = 'utf8') => {
        fs.writeFile(filePath, fileData, encoding, (err) => {
            if (err) {
                throw err;
            }
            callback();
        });
    };

    //Function that validates inputs before adding to database
    function addingNewWords(data, from, to, path){
        if(data.hasOwnProperty(from)) {
            //Check if translation already in list
            if(!data[from].translation.includes(to)) {
                data[from].translation.push(to)
            }
        }
        else{
            let object = { [from] : {"translation": [to]}};
            data[from]=object[from];
        }
        writeFile(JSON.stringify(data, null, 2), () => {}, path);
    }

    //Function returns all fuzzies to given word, where distance is between 1 and 3
    function getFuzzies(keys, word){
        let fuzzy = [];
        for (let i = 0; i < keys.length; i++) {
            let distance = levenshtein(word,keys[i]);
            if(distance>0 && distance<3){
                fuzzy.push(keys[i])
            }
        }
        return fuzzy;
    }

    // READ
    app.get("/:language/:word", (req, res) => {

        //:language - variable that shows from which language we want find translate
        //:word - variable that we want to translate

        const languagePath = req.params["language"] === "english" ? englishPath : estoniaPath;

        readFile(data => {
                const word = req.params["word"];
                let keys = Object.keys(data);
                let fuzzies = getFuzzies(keys,word)
                if(data.hasOwnProperty(word)) {
                    res.send(
                        {"translation": data[word]['translation'],
                         "fuzzy": fuzzies}
                        );
                }
                else{
                    res.send(
                        {"translation": [],
                            "fuzzy": fuzzies}
                    );
                }
            },
            true,languagePath);
    });

    //CREATE
    app.post("/:language", (req, res) => {
        const from = req.body.from;
        const to = req.body.to;

        const firstPath = req.params["language"] === "english" ? englishPath : estoniaPath;
        const secondPath = req.params["language"] !== "english" ? englishPath : estoniaPath;

        //We should add words to both dictionaries
        readFile(data => {
            //First of all we add to english dictionary
            addingNewWords(data,from,to,firstPath);
        }, true,firstPath);

        readFile(data => {
            //After that we add to estonian dictionary
            addingNewWords(data,to,from,secondPath);

        }, true,secondPath);

        //Everything is OK
        res.status(200).send("new words added");
    });
};

module.exports = translateRoutes;