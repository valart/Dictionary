import { mount } from '@vue/test-utils'
import DataDisplay from "../src/components/translator/DataDisplay";

describe('Mounted Inputs component', () => {
    var wrapper = mount(DataDisplay, {
        propsData: {
            fuzzy: [],
            translates: ["oranž","apelsin"],
            translatesFound: true,
            fuzziesFound: false,
            from: "ENGLISH"
        }
    });

    it('Get correct translations and correct title', () => {
        const title = wrapper.findAll('p').at(0).text();
        const translate1 = wrapper.findAll('p').at(1).text();
        const translate2 = wrapper.findAll('p').at(2).text();
        expect(title).toEqual("Translations");
        expect(translate1).toEqual("apelsin");
        expect(translate2).toEqual("oranž");
    })

    it('Get correct title for non existing fuzzies', () => {
        const title = wrapper.findAll('p').at(3).text();
        expect(title).toEqual("No Fuzzies found");

    })

});
