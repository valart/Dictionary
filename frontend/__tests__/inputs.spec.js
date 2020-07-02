import { mount } from '@vue/test-utils'
import Inputs from "../src/components/adding/Inputs";

describe('Mounted Inputs component', () => {
    var wrapper = mount(Inputs, {
        propsData: {
            from: "ESTONIA",
            to: "ENGLISH"
        }
    });

    it('Find text from-to languages', () => {
        const langauage1 = wrapper.findAll('label').at(0).text();
        const langauage2 = wrapper.findAll('label').at(1).text();
        expect(langauage2).toEqual("ENGLISH");
        expect(langauage1).toEqual("ESTONIA");
    })

    it('Find button text', () => {
        const buttonText = wrapper.findAll('md-button').at(0).text();
        expect(buttonText).toEqual("LISA");
    })

    it('Alerts should not be visible', () => {
        wrapper.setData({showSnackBar: true, alertDialog: true})
        expect(wrapper.find('md-dialog').isVisible()).toBe(true);
        expect(wrapper.find('md-snackbar').isVisible()).toBe(true);
    })

});
