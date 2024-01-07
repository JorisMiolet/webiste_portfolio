import Login from "@/components/login/login.vue";
import {mount} from "@vue/test-utils"
import {router} from "@/router";

let wrapper;




router.beforeEach(function() {
    wrapper = mount(Login);
});

it('renders properly', function() {
    expect(wrapper.element.children.length,
        `main page starting with ${wrapper.element.tagName} has no child elements`)
        .toBeGreaterThan(0);
    expect(wrapper.html(),
        'The header did not render the pageTitle')
        .toContain(wrapper.vm.pageTitle);
})
