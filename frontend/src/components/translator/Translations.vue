<template>

    <div>
        <div class="inputs">
            <div class="inputField">
                <md-field>
                    <label>{{from==="ENGLISH"?"Type a word":"Sisestage sõna"}}</label>
                    <md-input v-model="word"></md-input>
                </md-field>
            </div>
            <div class="buttons">
                <md-button class="md-raised md-primary" v-on:click="getTranslate">{{from==="ENGLISH"?"TRANSLATE":"TÕLGI"}}</md-button>
            </div>
        </div>

        <DataDisplay :fuzzy="fuzzy"
                     :translates="translates"
                     :translatesFound="translatesFound"
                     :fuzziesFound="fuzziesFound"
                     :from="from"
        />

        <md-snackbar :md-position="position" :md-duration="9000" :md-active.sync="showSnackBar" md-persistent>
            <span>Check typed form of word (no marks, no spaces, not empty)</span>
        </md-snackbar>

    </div>
</template>

<script>

    import axios from 'axios';
    import DataDisplay from "./DataDisplay";

    export default {
        name: "Translations",
        props: {
            from: String
        },
        components: {
            DataDisplay
        },
        data: () => ({
            translates: [],
            fuzzy: [],
            word: null,
            translatesFound:true,
            fuzziesFound:true,
            position: 'center',
            showSnackBar: false
        }),
        methods: {
            getTranslate: function(){
                let regex = /^[a-zA-ZäöüõÄÖÜÕ]*$/;
                if(regex.test(this.word) && this.word != null){
                    axios
                        .get('http://localhost:3000/'+this.from.toLowerCase()+'/'+this.word.toLowerCase())
                        .then(response => {
                            this.translates=response.data.translation;
                            this.fuzzy=response.data.fuzzy;

                            //Check if translates were found
                            if(this.translates.length===0)
                                this.translatesFound=false;
                            else
                                this.translatesFound=true;

                            //Check if fuzzies were found
                            if(this.fuzzy.length===0)
                                this.fuzziesFound=false;
                            else
                                this.fuzziesFound=true;
                        })
                        .catch(e => console.log(e));
                }
                else {
                    this.showSnackBar=true;
                }
            }
        }
    }
</script>

<style scoped>
    .translation{
        font-size: 28px;
    }
    .title{
        color: #990ae3;
        font-size: 20px;
    }
    .inputField{
        width: 75%;
    }
    .buttons{
        width: 20%;
    }
    .inputs{
        display: flex;
        flex-flow: row wrap;
        align-items: center;
    }
</style>
