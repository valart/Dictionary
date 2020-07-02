<template>
    <div id="app">
        <div class="center">
            <HeaderComponent  :name="from==='ENGLISH' ? 'ADDING NEW WORDS' : 'UUTE SÕNADE LISAMINE'" @clicked="languageChange"/>
            <Inputs :from="from" :to="to"/>
        </div>
    </div>
</template>

<script>

    import HeaderComponent from "../HeaderComponent";
    import Inputs from "./Inputs";

    export default {
        name: "Adding",
        components: {
            HeaderComponent,
            Inputs
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
