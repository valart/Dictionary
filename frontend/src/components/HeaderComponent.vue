<template>
    <div class="main">
        <h1 class="title">{{name}}</h1>
        <div style="display: flex;align-items: center; justify-content: center;">
            <div class="language">
                <p>{{from}}</p>
                <md-button class="md-icon-button" v-on:click="changelanguage">
                    <md-icon>compare_arrows</md-icon>
                </md-button>
                <p>{{to}}</p>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "HeaderComponent",
        data: () => ({
            from: "ENGLISH",
            to: "ESTONIA"
        }),
        props: {
            name: String
        },
        methods: {
            changelanguage: function(){
                let local = this.from;
                this.from=this.to;
                this.to=local;
                this.$emit('clicked', this.from)
            },
        }
    }
</script>

<style scoped>
    .title{
        color: #990ae3;
        text-align: center;
    }
    md-option{
        background-color: #990ae3;
    }
    .language{
        display: flex;
        align-items: center;
    }
    p{
        color: #448aff;
    }
</style>
