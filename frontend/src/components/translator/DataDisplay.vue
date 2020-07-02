<template>
    <div>
        <div v-if="translatesFound">
            <p v-if="translates.length>1" class="title">{{from==="ENGLISH"?"Translations":"Tõlged"}}</p>
            <p v-else-if="translates.length===1" class="title">{{from==="ENGLISH"?"Translation":"Tõlge"}}</p>
            <div v-for="word in translates.sort()" :key="word">
                <p class="translation">{{word}}</p>
            </div>
        </div>
        <div v-else>
            <p class="title">{{from==="ENGLISH"?"No matches":"Vasteid ei leitud"}}</p>
        </div>

        <div v-if="fuzziesFound">
            <p v-if="fuzzy.length>0" class="title">{{from==="ENGLISH"?"Fuzzy matches":"Sarnased sõnad"}}</p>
            <p v-else-if="fuzzy.length===1" class="title">{{from==="ENGLISH"?"Fuzzy match":"Sarnane sõna"}}</p>
            <div v-for="word in fuzzy.sort()" :key="word">
                <p class="translation">{{word}}</p>
            </div>
        </div>
        <div v-else>
            <p class="title">{{from==="ENGLISH"?"No Fuzzies found":"Sarnaseid sõnu ei leitud"}}</p>
        </div>

    </div>
</template>

<script>
    export default {
        name: "DataDisplay",
        props: {
            fuzzy: Array,
            translates: Array,
            translatesFound: Boolean,
            fuzziesFound: Boolean,
            from: String
        }
    }
</script>

<style scoped>
    .title{
        color: #990ae3;
        font-size: 20px;
    }
    .translation{
        font-size: 28px;
        width: 50%;
    }
</style>
