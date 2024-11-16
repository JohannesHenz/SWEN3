import { useEffect, useState } from 'react';
import { successToast, errorToast } from '../../utils';
import * as dmsapi from '../../store/api/DmsApi';

const Home = () => {
	const [dmsApiResponse, setDmsApiResponse] = useState();
	const [dmsList, setDmsList] = useState([]);
	const [dmsDetails, setDmsDetails] = useState();
	const [currentDmsId, setCurrentDmsId] = useState(null);
	const [file, setFile] = useState();

	const dmsStatus = () => {
		dmsapi.dmsApi().then((res)	=> {
			console.debug(res);
			if (res.data) {
				setDmsApiResponse(res.data.message);
			}
			successToast('Your email has been added to waitlist successfully');
		}).catch((err) => {
			if (err.response && err.response.data.message) {
				errorToast(err.response.data.message);
			} else {
				errorToast('Failed to add email to waitlist, please try again later');
			}
		});
	};
	const getList = () => {
		dmsapi.dmsList()
			.then((res) => {
				setDmsList(res.data);
			});
	};
	const getDetail = () => {
		dmsapi.dmsDetail(currentDmsId)
			.then((res) => {
				setDmsDetails(res.data);
			});
	};
	function handleChange(event) {
		setFile(event.target.files[0]);
	}

	function handleSubmit(event) {
		event.preventDefault();
		const formData = new FormData();
		formData.append('file', file);

		dmsapi.uploadDocument(formData)
			.then((response) => {
				console.log(response.data);
				event.target.reset();
			});
	}

	useEffect(() => {
		dmsStatus();
		getList();
	}, []);
	useEffect(() => {
		getDetail();
	}, [currentDmsId]);

	return (
		<>
			<div className='container px-6'>
				<div className='flex items-baseline gap-4'>
					<h1>DMS API Response: </h1>
					<p>{dmsApiResponse}</p>
				</div>
				<div className='mt-8'>
					<form onSubmit={handleSubmit}>
						<label className='mr-4'>Please select a pdf file</label>
						<input type="file" name="file" id="" onChange={handleChange}/>
						<button className='px-3 py-1 border-green-600 bg-green-600 text-white' type="submit">Submit</button>
					</form>
				</div>
				<div className='mt-8'>
					<h2>Uploaded documents</h2>
					<table className='table-auto text-sm font-light'>
						<thead>
							<tr>
								<th className='px-8'>FileName</th>
								<th className='px-8'>File Size</th>
								<th className='px-8'>Status</th>
								<th className='px-8'>Action</th>
							</tr>
						</thead>
						<tbody>
							{
								dmsList.map((it) => {
									return <tr key={it.id} className='py-2'>
										<td className='px-8'>{it.fileName}</td>
										<td className='px-8'>{it.fileSize}</td>
										<td className='px-8'>{it.status === 2 ? 'Processed' : 'Processing' }</td>
										<td className='px-8'>{ it.status === 2 ? <button onClick={() => setCurrentDmsId(it.id)} className='px-3 py-1 border-green-600 bg-green-600 text-white'>Show Content</button> : '' }</td>
									</tr>;
								})
							}
						</tbody>
					</table>
				</div>
				<div className='mt-8'>
					{dmsDetails
						? <><h1>Details for {dmsDetails.fileName}</h1>{dmsDetails.textContent}</> : ''}
				</div>
			</div>
		</>
	);
};
export { Home };
