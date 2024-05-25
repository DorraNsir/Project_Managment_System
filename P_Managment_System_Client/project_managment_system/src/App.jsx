
import { Route, Routes } from 'react-router-dom'
import './App.css'
import Home from './pages/Home/Home'
import Navbar from './pages/Navbar/Navbar'
import ProjectDetails from './ProjectDetails/ProjectDetails'
import IssueDetails from './IssueDetails/IssueDetails'
import Auth from './Auth/Auth'
import { useDispatch, useSelector } from 'react-redux'
import { useEffect } from 'react'
import { getUser } from './Redux/Auth/Action'
import { fetchProjects } from './Redux/Project/Action'



function App() {

const dispatch =useDispatch();
const {auth}=useSelector(store=>store)

useEffect(()=>{
  dispatch(getUser())
  dispatch(fetchProjects({}))
},[dispatch])
console.log("auuuuuth",auth)
  return (
    <>
    {
      //auth.user
      auth.user? <div>
                <Navbar/>
                <Routes>
                  <Route path="/" element={<Home/>}/>
                  <Route path="/project/:id" element={<ProjectDetails/>}/>
                  <Route path="/project/:projectId/issue/:issueId" element={<IssueDetails/>}/>
                </Routes>
      </div>:<Auth/>
    }

    </>
  )
}

export default App
