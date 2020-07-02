import { mount } from '@vue/test-utils'
import HeaderComponent from "../src/components/HeaderComponent";


describe('Mounted HeaderComponent', () => {
    var wrapper = mount(HeaderComponent);

    it('In the beginning first label is ENGLISH and second ESTONIA', () => {
        const langauage1 = wrapper.findAll('p').at(0).text();
        const langauage2 = wrapper.findAll('p').at(1).text();
        expect(langauage1).toEqual("ENGLISH");
        expect(langauage2).toEqual("ESTONIA");
        //Click swipe button for next test
        wrapper.find('md-button').trigger('click');
    })

    it('After press change labels', () => {

        const langauage1 = wrapper.findAll('p').at(0).text();
        const langauage2 = wrapper.findAll('p').at(1).text();
        expect(langauage1).toEqual("ESTONIA");
        expect(langauage2).toEqual("ENGLISH");
    })

});

