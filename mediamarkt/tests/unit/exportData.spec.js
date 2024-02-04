import exportData from "@/components/admin/pages/image/exportData.vue";
import { shallowMount } from '@vue/test-utils';


describe('ExportData', () => {

    // Test 1: Mount the component and check if it renders correctly
    it('renders correctly', () => {
        const wrapper = shallowMount(exportData);
        expect(wrapper.element).toMatchSnapshot();
    });

    // Test 2: Test the createOffer method
    it('creates an offer when createOffer method is called', () => {
        // Arrange data
        const wrapper = shallowMount(exportData);
        const image = {
            "ArticleNR": undefined, "Description": undefined, "EAN": "1234567890", "GPU": "NVIDIA GeForce GTX 1650", "PCBrand": undefined, "PROCESSOR": "Intel Core i7", "RAM": "16GB", "STORAGE": "512GB SSD"
        };

        // call function
        wrapper.vm.create_image(image);

        // Assert that the images array is updated with the new image
        expect(wrapper.vm.images).toEqual([image]);
    });

    // Test 3: Test the deleteImage method
    it('deletes an image when deleteImage method is called', async () => {
        const wrapper = shallowMount(exportData);
        const image = {
            "ArticleNR": undefined, "Description": undefined, "EAN": "1234567890", "GPU": "NVIDIA GeForce GTX 1650", "PCBrand": undefined, "PROCESSOR": "Intel Core i7", "RAM": "16GB", "STORAGE": "512GB SSD"
        };
        // Mock the axios delete method
        const axiosDeleteMock = jest.fn(() => Promise.resolve());
        wrapper.vm.axios.post = axiosDeleteMock;

        // Call the deleteImage method
        await wrapper.vm.deleteImage(image, axiosDeleteMock());

        // Assert that the loadAllImages method is called after deletion
        expect(wrapper.vm.axiosDeleteMock).toHaveBeenCalled();
    });

    // Test 4: Test the filterImages method
    it('filters images when filterImages method is called', async () => {
        const wrapper = shallowMount(exportData);
        const image = [{    "ArticleNR": undefined, "Description": undefined, "EAN": "1234567890", "GPU": "NVIDIA GeForce GTX 1650", "PCBrand": undefined, "PROCESSOR": "Intel Core i7", "RAM": "16GB", "STORAGE": "512GB SSD"
        }]
        jest.mock('axios');

        // Mock the axios get method
        const axiosGetMock = jest.fn(() => Promise.resolve({ data: image}));
        wrapper.vm.axios.get = axiosGetMock;

        // Call the deleteImage method
        await wrapper.vm.filterImages();

        // Assert that the loadAllImages method is called after deletion
        expect(wrapper.vm.loadAllImages).toHaveBeenCalled();
    });

    // Test 5: Test the loadAllImages method
    it('loads all images when loadAllImages method is called', async () => {
        // Arrange data
        const wrapper = shallowMount(exportData);
        const image = [{
            "ArticleNR": undefined, "Description": undefined, "EAN": "1234567890", "GPU": "NVIDIA GeForce GTX 1650", "PCBrand": undefined, "PROCESSOR": "Intel Core i7", "RAM": "16GB", "STORAGE": "512GB SSD"
        }]

        // Mock the axios get method
        const axiosGetMock = jest.fn(() => Promise.resolve({ data: image }));
        wrapper.vm.axios.get = axiosGetMock;

        // Call the loadAllImages method
        await wrapper.vm.loadAllImages();

        // Assert that the images array is updated with the loaded data
        expect(wrapper.vm.images).toEqual([{
            "ArticleNR": undefined, "Description": undefined, "EAN": "1234567890", "GPU": "NVIDIA GeForce GTX 1650", "PCBrand": undefined, "PROCESSOR": "Intel Core i7", "RAM": "16GB", "STORAGE": "512GB SSD"
        }]);
    });
});