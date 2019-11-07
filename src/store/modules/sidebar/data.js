// Sidebar Routers
export const menus = {
	'message.general': [
		{
			action: 'zmdi-view-dashboard',
			title: 'message.dashboard',
			active: true,
			label: 'New',
			items: [
				{ title: 'message.ecommerce', path: '/default/dashboard/ecommerce', exact: true,label: 'Old' }
			]
		},
      {
			action: 'zmdi zmdi-accounts-alt',
			title: 'message.crm',
			active: false,
			label: 'New',
			items: [
				{ title: 'message.projects', path: '/crm/projects',label: 'New'},
				{ title: 'message.projectDetails', path: '/crm/projectDetails/01',label: 'New'},
			]
      },

	],
	'message.modules': [

		{
			action: 'zmdi-comments',
			title: 'message.chat',
			active: false,
			items: null,
			path: '/chat',
			label: 'Old',
		}
	],
	'message.components': [
		{
			action: 'zmdi-wrench',
			title: 'message.uiElements',
			active: false,
			label: 'New',
			items: [
				{ title: 'message.appBars', path: '/ui-elements/app-bars',label: 'New' },
				{ title: 'message.banners', path: '/ui-elements/banners',label: 'New' },
				{ title: 'message.listItemGroups', path: '/ui-elements/list-item-groups',label: 'New' },
				{ title: 'message.slideGroups', path: '/ui-elements/slide-groups',label: 'New' },
				{ title: 'message.overlays', path: '/ui-elements/overlays',label: 'New' },
				{ title: 'message.chipGroups', path: '/ui-elements/chip-groups',label: 'New' },
				{ title: 'message.colorPickers', path: '/ui-elements/color-pickers',label: 'New' },
				{ title: 'message.fileInput', path: '/ui-elements/file-input',label: 'New' },
				{ title: 'message.groups', path: '/ui-elements/groups',label: 'Old' },
				{ title: 'message.buttons', path: '/ui-elements/buttons',label: 'Old' },
				{ title: 'message.cards', path: '/ui-elements/cards',label: 'Old'},
				{ title: 'message.checkbox', path: '/ui-elements/checkbox' ,label: 'Old'},
				{ title: 'message.carousel', path: '/ui-elements/carousel' ,label: 'Old'},
				{ title: 'message.chips', path: '/ui-elements/chips' ,label: 'Old'},
				{ title: 'message.datepicker', path: '/ui-elements/datepicker',label: 'Old' },
				{ title: 'message.dialog', path: '/ui-elements/dialog' ,label: 'Old'},
				{ title: 'message.grid', path: '/ui-elements/grid',label: 'Old' },
				{ title: 'message.hover', path: '/ui-elements/hover',label: 'Old' },
				{ title: 'message.images', path: '/ui-elements/images',label: 'Old' },
				{ title: 'message.input', path: '/ui-elements/input',label: 'Old' },
				{ title: 'message.list', path: '/ui-elements/list',label: 'Old' },
				{ title: 'message.menu', path: '/ui-elements/menu' ,label: 'Old'},
				{ title: 'message.progress', path: '/ui-elements/progress' ,label: 'Old'},
				{ title: 'message.ratings', path: '/ui-elements/ratings',label: 'Old' },
				{ title: 'message.radio', path: '/ui-elements/radio',label: 'Old' },
				{ title: 'message.select', path: '/ui-elements/select',label: 'Old' },
				{ title: 'message.slider', path: '/ui-elements/slider',label: 'Old' },
				{ title: 'message.snackbar', path: '/ui-elements/snackbar',label: 'Old' },
				{ title: 'message.tabs', path: '/ui-elements/tabs',label: 'Old' },
				{ title: 'message.toolbar', path: '/ui-elements/toolbar',label: 'Old' },
				{ title: 'message.tooltip', path: '/ui-elements/tooltip',label: 'Old' },
				{ title: 'message.timepicker', path: '/ui-elements/timepicker',label: 'Old' }
			]
		},

		{
			action: 'zmdi-flag',
			title: 'message.icons',
			active: false,
			label:'Old',
			items: [
				{ title: 'message.themify', path: '/icons/themify',label:'Old' },
				{ title: 'message.material', path: '/icons/material',label:'Old'}
			]
		},
		{
			action: 'zmdi-grid',
			title: 'message.tables',
			active: false,
			label:'New',
			items: [
				{ title: 'message.simple', path: '/tables/simple',label:'New'},
				{ title: 'message.standard', path: '/tables/standard',label:'Old'},
				{ title: 'message.slots', path: '/tables/slots',label:'Old'},
				{ title: 'message.selectable', path: '/tables/selectablerows',label:'Old'},
				{ title: 'message.searchRow', path: '/tables/searchwithtext',label:'Old'}
			]
		},
	],
	'message.applications': [
		{
			action: 'zmdi-accounts',
			title: 'message.users',
			active: false,
			label:'Old',
			items: [
				{ title: 'message.userProfile', path: '/users/user-profile',label:'Old'},
				{ title: 'message.usersList', path: '/users/users-list',label:'Old'}
			]
		}
	],

	'message.extensions': [

		{
			action: 'zmdi-dropbox',
			title: 'message.dropzone',
			active: false,
			items: null,
			path: '/dropzone',
			label:'Old'
		}
	]
}
