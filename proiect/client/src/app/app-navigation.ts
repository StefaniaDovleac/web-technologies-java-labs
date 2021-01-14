export const navigation = [
  {
    text: 'Dashboard',
    path: '/directories/all',
    icon: 'home'
  },
  {
    text: 'Favorites',
    icon: 'favorites',
    path: '/favorites'
  },
  {
    text: 'Manager',
    icon: 'folder',
    items: [
      {
        text: 'Register',
        icon: 'group',
        path: '/users/register'
      }
    ]
  }
];
