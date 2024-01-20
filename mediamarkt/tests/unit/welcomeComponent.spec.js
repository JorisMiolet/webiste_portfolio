import WelcomeComponent from "../../src/components/Homepage/welcomeComponent.vue";
import {shallowMount} from "@vue/test-utils";

// Describing the test suite for the WelcomeComponent.vue
describe("WelcomeComponent.vue", () => {

    // Test case: Renders properly when mounted
    it("renders properly when mounted", () => {
        // Arrange
        const wrapper = shallowMount(WelcomeComponent);

        // Act
        // Nothing to act upon, as it's a rendering test

        // Assert
        expect(wrapper.exists()).toBe(true);
    });

    // Test case: 'buttonClicked' is set to false initially
    it("has 'buttonClicked' set to false initially", () => {
        // Arrange
        const wrapper = shallowMount(WelcomeComponent);

        // Act
        // Nothing to act upon, as it's a state initialization test

        // Assert
        expect(wrapper.vm.buttonClicked).toBe(false);
    });


    // Test case: Emits 'scan-clicked' event when HeaderComponent is clicked
    it("emits 'scan-clicked' event when HeaderComponent is clicked", async () => {
        // Arrange
        const wrapper = shallowMount(WelcomeComponent);

        // Act
        // Find the HeaderComponent within the WelcomeComponent
        const headerComponent = wrapper.findComponent({ name: "HeaderComponent" });

        // Trigger the 'scan-clicked' event on the HeaderComponent
        await headerComponent.trigger("scan-clicked");

        // Assert
        // Expectation: 'buttonClicked' should be true after the event is triggered
        expect(wrapper.vm.buttonClicked).toBe(true);
    });

});


