// const artists = [
//   {
//       artistId: 1,
//       name: "Jim Swim",
//       managerId: 1,
//       emailAddress: "jimSwim93@gmail.com",
//       userId: 3,
//       imageUrl: "../src/assets/profile-pics/jimSwim.jpg", 
//       pro : '177628902'
//   },
  
//   {
//       artistId: 2,
//       name: "ADE",
//       managerId: 2,
//       emailAddress: "AD3music@hotmail.com",
//       userId: 2,
//       imageUrl: "../src/assets/profile-pics/Ade.jpg",
//       pro: '1762803782'
//   },
  
//   {
//       artistId: 3,
//       name: "LevI",
//       managerId: 2,
//       emailAddress: "LiveLevi2@yahoo.com",
//       userId: 5,
//       imageUrl: "../src/assets/profile-pics/Levi.jpg",
//       pro: '8298710029'
//   }
// ];

// const projects = [
//   {
//       projectId: 1,
//       projectName: "Ocean Blue",
//       releaseDate: "2024-09-14",
//       description: "Album",
//       completed: true
//   },
//   {
//       projectId: 2,
//       projectName: "Sapphire Sky",
//       releaseDate: "2025-07-08",
//       description: "Album",
//       completed: false

//   },
//   {
//       projectId: 3,
//       projectName: "Coral Reef",
//       releaseDate: "2025-10-19",
//       description: "Album",
//       completed: false
//   },
//   {
//       projectId: 4,
//       projectName: "Starlight",
//       releaseDate: "2025-11-03",
//       description: "Album",
//       completed: false
//   },
//   {
//       projectId: 5,
//       projectName: "Ambery Lite",
//       releaseDate: "2025-10-13",
//       description: "Single",
//       completed: false
//   },
//   {
//       projectId: 6,
//       projectName: "Thunderbird",
//       releaseDate: "2024-07-23",
//       description: "Album",
//       completed: false

//   },
//   {
//       projectId: 7,
//       projectName: "Gold Coast Canyon",
//       releaseDate: "2025-11-20",
//       description: "Album",
//       completed: false
//   },
//   {
//       projectId: 8,
//       projectName: "Mystic Mirage",
//       releaseDate: "2025-05-30",
//       description: "Album",
//       completed: false
//   },
//   {
//       projectId: 9,
//       projectName: "Moonshot Initiative",
//       releaseDate: "2024-09-18",
//       description: "Single",
//       completed: false
//   },
//   {
//       projectId: 10,
//       projectName: "Visions of You",
//       releaseDate: "2025-10-21",
//       description: "Single",
//       completed: false
//   },
//   {
//       projectId: 11,
//       projectName: "Copper Canyon",
//       releaseDate: "2025-01-20",
//       description: "Album",
//       completed: false
//   },
//   {
//       projectId: 20,
//       projectName: "Love Brawl",
//       releaseDate: "2025-08-08",
//       description: "Album",
//       completed: false
//   }
// ];

// const artistProjectIds = [
//   { artistId: 3, projectId: 1 },
//   { artistId: 1, projectId: 3 },
//   { artistId: 2, projectId: 5 },
//   { artistId: 2, projectId: 2 },
//   { artistId: 1, projectId: 10 },
//   { artistId: 2, projectId: 7 },
//   { artistId: 3, projectId: 4 },
//   { artistId: 3, projectId: 6 },
//   { artistId: 2, projectId: 11 },
//   { artistId: 1, projectId: 8 },
//   { artistId: 1, projectId: 9 },
//   { artistId: 2, projectId: 20 }
// ];

// const managers = [
//   {
//       managerId: 0,
//       name: "0 - No Management",
//       emailAddress: "",
//       userId: 1,
//       imageUrl : "../src/assets/profile-pics/empty.png",
//   },
//   {
//       managerId: 1,
//       name: "Malissa Twidale",
//       emailAddress: "mtwidale0@mit.edu",
//       userId: 4,
//       imageUrl: "../src/assets/profile-pics/Melissa.jpg",
//   },
//   {
//       managerId: 2,
//       name: "Ofelia Keeney",
//       emailAddress: "okeeney1@bandcamp.com",
//       userId: 6,
//       imageUrl: "../src/assets/profile-pics/ofelia.jpg",
//   }
// ];

// const users = [
//   {
//       userId: 1,
//       username: "admin",
//       role: "ROLE_ADMIN",
//       enabled: true,
//       user_type: 'Admin'
//   },
//   {
//       userId: 2,
//       username: "bajose1029",
//       role: "ROLE_USER",
//       enabled: true,
//       user_type: 'Artist'
//   },
//   {
//       userId: 3,
//       username: "jimSwim11",
//       role: "ROLE_USER",
//       enabled: true,
//       user_type: 'Artist'
//   },
//   {
//       userId: 4,
//       username: "leviToCuz90",
//       role: "ROLE_USER",
//       enabled: true,
//       user_type: 'Artist'
//   },

//   {
//       userId: 5,
//       username: "mTwidale65",
//       role: "ROLE_USER",
//       enabled: true,
//       user_type: 'Manager'
//   },
//   {
//       userId: 6,
//       username: "OfeliaK0K",
//       role: "ROLE_USER",
//       enabled: true,
//       user_type: 'Manager'
//   }
// ];

import axios from 'axios'

const resourceService = {
  updateProject(project, projectId){
    return axios.put(`/projects/${projectId}`, project);
  },

  deleteProject(projectId){
    return axios.delete(`/projects/${projectId}`);
  },
  addArtistsProject(project) {
    return axios.post('/projects', project)
  },

  getArtists() {
    return axios.get('/artists');
  },

  getProjects() {
    return axios.get('/projects');
  },

  getManagers() {
    return axios.get('/managers');
  },

  getUsers() {
    return axios.get('/users');
  },

  getAnArtistsProjects(artistId) {

    return axios.get(`/projects/artists/${artistId}`);
    // // let listOfProjectId = [];
    // // const listOfArtistProjectIds = artistProjectIds;
    // // const listOfProjects = axios.get('/projects');
    // // let anArtistsProjects = [];

    // // for(let i = 0, n = listOfArtistProjectIds.length; i < n; i++){
    // //   if(listOfArtistProjectIds[i].artistId === artistId){
    // //     listOfProjectId.push(listOfArtistProjectIds[i].projectId);
    // //   }
    // // }

    // // if(listOfProjectId.length === 0) {
    // //   console.log("This artist does not have any projects available");
    // // }
    // // else {
    // //   anArtistsProjects = listOfProjects.filter((project) => {
    // //     return listOfProjectId.includes(project.projectId);
    // //   });
    // }

    // return anArtistsProjects;
  },

  getManagerFromArtistId(artistId) {
    // let fullArtistList = axios.get('/artists');
    // let fullManagerList = axios.get('/managers');
    
    // let theArtist = fullArtistList.find((artist) => {
    //   return artist.artistId === artistId;
    // })
    
    // let theManager = fullManagerList.find((manager) => {
    //   return theArtist.managerId === manager.managerId;
    // })
    // return theManager;
    return axios.get(`/managers/artist/${artistId}`);
  },

  getArtistsFromManagerId(managerId) {
      return axios.get(`artists/manager/${managerId}`);
  },

  getManagerFromManagerId(managerId) {
    return axios.get(`/managers/${managerId}`);
  },

  getPersonFromUserId(userId) {
    let people = this.getBothArtistAndManagers();

    return people.find((person) => {return person.userId === userId})
  },

  getManagerFromUserId(userId) {
    return axios.get(`/managers/user/${userId}`);
  },

  getArtistFromUserId(userId) {
    return axios.get(`/artists/user/${userId}`)
  },

  getArtistFromArtistId(artistId){
    return axios.get(`/artists/${artistId}`);
  },

  getProjectFromProjectId(projectId) {
    return axios.get(`/projects/${projectId}`);
  },

  getBothArtistAndManagers() {
    let allArtistsAndManagers = [];
    let allArtists = [];
    let allManagers = [];

    axios.get('/artists').then((response) => {
      allArtists = response.data;
    })

    axios.get('/managers').then((response) => {
      allManagers = response.data;
    })


    allArtists.forEach((artist) => {
      allArtistsAndManagers.push(artist);
    });

    allManagers.forEach((manager) => {
      allArtistsAndManagers.push(manager);
    });

    allArtistsAndManagers.sort((a,b) => {
      return a.name.localeCompare(b.name);
    });

    return allArtistsAndManagers;
  }
};

export { resourceService };
