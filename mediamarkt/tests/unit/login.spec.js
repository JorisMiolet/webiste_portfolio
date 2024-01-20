import {mount, shallowMount} from '@vue/test-utils';
import LogIn from '@/components/login/LogIn.vue';

// Test Case 1: Test whether the component renders correctly
describe('LogIn.vue', () => {
    it('renders the component', () => {
        // Arrange
        const wrapper = shallowMount(LogIn);

        // Act
        // Nothing to act upon, as it's a rendering test

        // Assert
        expect(wrapper.exists()).toBe(true);

        // Clean up
        wrapper.unmount();
    });
});
//faster than 200ms
//takes care of 1 task (isolated)
//it does not matter how many times I repeat my test, it wil yield the same result. (Repeatable)
//I don't know how to add error messages in jest without consulting external library's.
//it grows with the size of the application(Timely)
describe('LogIn.vue', () => {
    it('displays error message on failed login attempt', async () => {
        // Arrange
        global.fetch = jest.fn(() =>
            Promise.reject(new Error('Network error'))
        );

        const wrapper = shallowMount(LogIn);

        // Act
        // Set input values
        await wrapper.setData({
            emailInput: 'test@example.com',
            passwordInput: 'testpassword',
        });

        // Trigger button click
        await wrapper.find('button').trigger('click');

        // Wait for asynchronous operations to complete
        await wrapper.vm.$nextTick();

        // Assert
        expect(wrapper.vm.errorMessage).toBe('Er is een fout opgetreden');

        // Clean up
        wrapper.unmount();
    });
});

