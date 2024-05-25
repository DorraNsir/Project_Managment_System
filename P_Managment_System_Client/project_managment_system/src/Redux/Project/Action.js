import api from "@/config/api"
import { ACCEPT_INVITATION_REQUEST, ACCEPT_INVITATION_SUCCESS, CREATE_PROJECT_REQUEST, CREATE_PROJECT_SUCCESS, DELETE_PROJECT_REQUEST, DELETE_PROJECT_SUCCESS, FETCH_PROJECT_BY_ID_REQUEST, FETCH_PROJECT_BY_ID_SUCCESS, FETCH_PROJECTS_SUCCESS, INVITE_TO_PROJECT_REQUEST, INVITE_TO_PROJECT_SUCCESS, SEARCH_PROJECT_FAILURE, SEARCH_PROJECT_REQUEST, SEARCH_PROJECT_SUCCESS } from "./ActionTypes"

export const fetchProjects = ({ category, tag }) => async (dispatch) => {
    try {
        const jwt = localStorage.getItem('jwt');
        console.log("Fetching projects with JWT:", jwt); // Log the JWT token

        const { data } = await api.get("/api/projects", {
            params: { category, tag, jwt }
        });

        console.log("Fetched Projects Dataaa:", data); // Log the response data
        dispatch({ type: FETCH_PROJECTS_SUCCESS, projects: data });
    } catch (error) {
        console.log("Error fetching projects", error);
    }
};


export const searchProjects = (keyword) => async (dispatch) => {
    if (!keyword) {
        console.error("Keyword is empty");
        return;
    }
    dispatch({ type: SEARCH_PROJECT_REQUEST });
    try {
        const { data } = await api.get(`/api/projects/search?keyword=${keyword}`);
        console.log("search projects", data);
        dispatch({ type: SEARCH_PROJECT_SUCCESS, projects: data });
    } catch (error) {
        console.error("Error occurred while searching projects:", {
            data: error.response.data,
            status: error.response.status,
            headers: error.response.headers,
            config: error.response.config,
        });
        dispatch({ type: SEARCH_PROJECT_FAILURE, error: error.response ? error.response.data : error.message });
    }
};

export const fetchProjectById=(id)=>async(dispatch)=>{
    dispatch({type:FETCH_PROJECT_BY_ID_REQUEST})
    try{
        const{data}=await api.get("/api/projects/"+id)
        console.log("fetchProjectById",data)
        dispatch({type:FETCH_PROJECT_BY_ID_SUCCESS,projects:data})
    }catch(error){
        console.log("error",error)
    }
}

export const createProject = (projectData) => async (dispatch) => {
    dispatch({ type: CREATE_PROJECT_REQUEST });
    try {
        const { data } = await api.post("/api/projects", projectData, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        console.log("projectsCreate_____", data);
        dispatch({ type: CREATE_PROJECT_SUCCESS, project: data });
    } catch (error) {
        console.log("error", error);
    }
};

export const deleteProject=({projectId})=>async(dispatch)=>{
    dispatch({type:DELETE_PROJECT_REQUEST})
    try{
        const{data}=await api.delete("/api/projects/"+projectId)
        console.log("delete projects",data)
        dispatch({type:DELETE_PROJECT_SUCCESS,projectId})
    }catch(error){
        console.log("error",error)
    }
}

export const inviteToProject=({email,projectId})=>async(dispatch)=>{
    dispatch({type:INVITE_TO_PROJECT_REQUEST})
    try{
        const{data}=await api.post("/api/projects/invite",{email,projectId})
        console.log("ivite projects",data)
        dispatch({type:INVITE_TO_PROJECT_SUCCESS,payload:data})
    }catch(error){
        console.log("error",error)
    }
}

export const acceptInvitation=({invitationToken,navigate})=>async(dispatch)=>{
    dispatch({type:ACCEPT_INVITATION_REQUEST})
    try{
        const{data}=await api.get("/api/projects/accept_invitation",{
            params:{
                token:invitationToken
            }
        })
        navigate("/projects"+data.projectId)
        console.log("accept invitation",data)
        dispatch({type:ACCEPT_INVITATION_SUCCESS,payload:data})
    }catch(error){
        console.log("error",error)
    }
}





