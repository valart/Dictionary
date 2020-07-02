<template>
    <div class="inputs">
        <md-field>
            <label>{{from}}</label>
            <md-input v-model="fromWord"></md-input>
        </md-field>
        <md-field>
            <label>{{to}}</label>
            <md-input v-model="toWord"></md-input>
        </md-field>
        <md-button class="md-raised md-primary" v-on:click="addWords">{{from==="ENGLISH"?"ADD":"LISA"}}</md-button>

        <md-dialog :md-active.sync="alertDialog" :md-fullscreen="false">
            <md-dialog-title>Oops!</md-dialog-title>
            <p> - Every input field should be filled</p>
            <p> - Every input field should contain only letters (no commas, no spaces)</p>
            <p> - Only one translation can be added at a time</p>
            <md-dialog-actions>
                <md-button class="md-primary" @click="alertDialog = false">Close</md-button>
            </md-dialog-actions>
        </md-dialog>

        <md-snackbar :md-position="position" :md-duration="2500" :md-active.sync="showSnackBar" md-persistent>
            <span>New words were added</span>
        </md-snackbar>

    </div>
</template>

<script>

    import axios from 'axios'

    export default {
        name: "Inputs",
        props: {
            from: String,
            to: String
        },
        data: () => ({
            fromWord: null,
            toWord: null,
            showSnackBar: false,
            alertDialog: false,
            position: "center"
        }),
        methods: {
            addWords: function(){
                let regex = /^[a-zA-ZäöüõÄÖÜÕ]*$/;
                if(regex.test(this.fromWord) && regex.test(this.toWord) &&
                    this.fromWord != null && this.toWord != null) {
                    let model = {
                        from: this.fromWord.toLowerCase(),
                        to: this.toWord.toLowerCase()
                    };
                    axios.post('http://localhost:3000/'+this.from.toLowerCase(), model)
                        .then(() => {this.showSnackBar=true});
                    this.fromWord=null;
                    this.toWord=null;
                }
                else {
                    this.alertDialog=true;
                }
            }
        }
    }
</script>

<style scoped>
    .inputs{
        padding: 25px;
    }
    p{
        padding-left: 15px;
        padding-right: 15px;
    }
</style>
