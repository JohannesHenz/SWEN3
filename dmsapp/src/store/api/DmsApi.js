import axiosConfig from '../../utils/axiosConfig';

export const dmsApi = () => {
	return axiosConfig.get('');
};

export const dmsList = () => {
	return axiosConfig.get('/list');
};

export const dmsDetail = (id) => {
	return axiosConfig.get(`/detail/${id}`);
};

export const uploadDocument = (data) => {
	return axiosConfig.post('/upload', data);
};
