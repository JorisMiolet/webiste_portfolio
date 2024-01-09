import { mount } from '@vue/test-utils';
import ExportData from '@/components/admin/pages/image/exportData.vue';

describe('ExportData', () => {
    it('renders correctly', () => {
        const wrapper = mount(ExportData, {
            propsData: {
                isAdmin: false, // Add any necessary props for your component
            },
            global: {
                provide: {
                    url: 'your_mocked_url', // Provide a mocked value for the 'url' injection
                },
            },
        });

        // Your test assertions go here
        expect(wrapper.text()).toContain('Manage images');
    });

    it('calls loadAllImages on created hook', async () => {
        const loadAllImagesMock = jest.fn();
        const wrapper = mount(ExportData, {
            propsData: {
                isAdmin: false,
            },
            global: {
                provide: {
                    url: 'your_mocked_url',
                },
            },
            methods: {
                loadAllImages: loadAllImagesMock,
            },
        });

        await wrapper.vm.$nextTick();

        expect(loadAllImagesMock).toHaveBeenCalled();
    });

    // Add more test cases for other methods, computed properties, and interactions
});
