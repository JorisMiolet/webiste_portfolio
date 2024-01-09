import WelcomeComponent from "@/components/Homepage/welcomeComponent.vue";
import {shallowMount} from "@vue/test-utils";

describe("WelcomeComponent.vue", () => {
    it("renders properly when mounted", () => {
        const wrapper = shallowMount(WelcomeComponent);
        expect(wrapper.exists()).toBe(true);
    });

    it("hides BarcodeComponent initially", () => {
        const wrapper = shallowMount(WelcomeComponent);
        const barcodeComponent = wrapper.findComponent({ name: "BarcodeComponent" });
        expect(barcodeComponent.exists()).toBe(false);
    });

    it("emits 'scan-clicked' event when HeaderComponent is clicked", async () => {
        const wrapper = shallowMount(WelcomeComponent);
        const headerComponent = wrapper.findComponent({ name: "HeaderComponent" });

        await headerComponent.trigger("scan-clicked");

        expect(wrapper.vm.buttonClicked).toBe(true);
    });
});


