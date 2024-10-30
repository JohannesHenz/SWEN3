import { Logo } from '../../assets/images/logo';

const Navbar = () => {
	return (
		<header>
			<div className='bg-white py-4 px-6 container text-primary-text font-medium text-base'>
				<div className='flex w-full justify-between'>
					<div className='flex items-center gap-6'>
						<img className='w-28' src={Logo} alt="logo" />
						<span className='h-6 w-[1px] bg-white opacity-20'></span>
					</div>

				</div>
			</div>
		</header>
	);
};

export { Navbar };
