<template>
    <div id="app">
        <div class="center">
            <HeaderComponent :name="from==='ENGLISH' ? 'DICTIONARY' : 'SÕNASTIK'" @clicked="languageChange"/>
            <Translations :from="from"/>
        </div>
    </div>
</template>

<script>

    import HeaderComponent from "../HeaderComponent";
    import Translations from "./Translations";

    export default {
        name: "Translator",
        components: {
            HeaderComponent,
            Translations
        },
        data: () => ({
            from: "ENGLISH",
            to: "ESTONIA"
        }),
        methods: {
            languageChange (value) {
                this.to=this.from;
                this.from=value;
            }
        }
    }
</script>

<style scoped>

    #app {
        width: 100%;
    }
    .center{
        width: 50%;
        padding: 10px;
        margin: auto;
        background: #f2f2f2;
    }
    @media screen and (max-width: 800px) {
        .center{
            width: 100%;
        }
    }

</style>
