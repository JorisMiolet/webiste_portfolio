// ImageComponent.spec.js
import { shallowMount } from '@vue/test-utils';

import ImageComponent from "@/components/Homepage/imageComponent";

// Describe a test suite for the ImageComponent
describe('ImageComponent', () => {

    // Test: Check if the component renders correctly
    it('renders correctly', () => {
        // Shallow mount the ImageComponent
        //arrange
        const wrapper = shallowMount(ImageComponent);
        // Expect that the wrapper exists (i.e., component renders without errors)
        expect(wrapper.exists()).toBe(true, 'Component should render without errors');
    });

    // Test: Ensure the component initializes with an empty laptops array
    it('initializes with empty laptops array', () => {
        // Shallow mount the ImageComponent
        //arrange
        const wrapper = shallowMount(ImageComponent);
        // Expect that the laptops data property is an empty array
        expect(wrapper.vm.laptops).toEqual([], 'laptops should be an empty array');
    });


    // Test: Check if setSelectedImage method sets the selectedImageInfo property on click
    it('sets selected image on click', () => {
        // Shallow mount the ImageComponent
        const wrapper = shallowMount(ImageComponent);
        // Create an image object
        const image = { id: 1, EAN: '123456789', Brand: 'Example Brand' };
        // Call the setSelectedImage method with the image
        wrapper.vm.setSelectedImage(image);
        // Expect that the selectedImageInfo property is equal to the image
        expect(wrapper.vm.selectedImageInfo).toEqual(image, 'selectedImageInfo should be equal to the image object');
    });

    // Test: Verify that resetImage method sets selectedImageInfo to null
    it('resets selected image on resetImage method', () => {
        // Shallow mount the ImageComponent
        const wrapper = shallowMount(ImageComponent);
        // Set a sample selectedImageInfo
        wrapper.vm.selectedImageInfo = { id: 1, EAN: '123456789', Brand: 'Example Brand' };
        // Call the resetImage method
        wrapper.vm.resetImage();
        // Expect that selectedImageInfo is null after calling resetImage
        expect(wrapper.vm.selectedImageInfo).toBeNull('selectedImageInfo should be null after calling resetImage');
    });

});
