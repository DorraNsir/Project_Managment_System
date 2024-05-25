import { Avatar, AvatarFallback } from "@/components/ui/avatar"
import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import { Dialog, DialogClose, DialogContent, DialogHeader, DialogTrigger } from "@/components/ui/dialog"
import { ScrollArea } from "@/components/ui/scroll-area"
import InviteUserForm from "./InviteUserForm"
import IssueList from "./IssueList"
import ChatBox from "./ChatBox"
import { useEffect } from "react"
import { useDispatch, useSelector } from "react-redux"
import { fetchProjectById } from "@/Redux/Project/Action"
import { useParams } from "react-router-dom"

const ProjectDetails = () => {
    const dispatch=useDispatch();
    const {project}=useSelector(store=>store)
    console.log("ProjectDetails___________",project)
    const{id}=useParams();
    const handleProjectInvitation=()=>{
        
    }
    useEffect(()=>{
        dispatch(fetchProjectById(id))
    },[id])
  return (
<>
<div className="mt-5 lg:px-10">
    <div className="lg:flex gap-5 justify-between pb-4">
        <ScrollArea className="h-screen g:w-[69%] pr-2">
            <div className="text-violet-900 pb-10 w-full">
                <h1 className="text-lg font-semibold pb-5">{project.ProjectDetails?.name}</h1>
                <div className="space-y-5 pb-10">
                    <p className="w-full md:max-w-lg lg:max-w-xl text-sm">{project.ProjectDetails?.description}</p>
                    <div className="flex">
                        <p className="w-36">Project Lead : </p>
                        <p>{project.ProjectDetails?.owner.fullName}</p>
                    </div>
                    <div className="flex">
                        <p className="w-36">Members : </p>
                        <div className="flex items-center gap-2">
                            {project.ProjectDetails?.team.map((item)=>
                            <Avatar className="cursor-pointer" key={item}>
                                <AvatarFallback>{item.fullName[0]}</AvatarFallback>
                            </Avatar>
                        )}
                        </div>
                        <Dialog>
                            <DialogTrigger>
                                <DialogClose>
                                    <Button size="sm" vaiant="outline" onClick={handleProjectInvitation} className="ml-2 ">
                                        <span>invite+</span>
                                    </Button>
                                </DialogClose>
                            </DialogTrigger>
                            <DialogContent>
                                <DialogHeader>Invite User</DialogHeader>
                                <InviteUserForm/>
                            </DialogContent>
                        </Dialog>

                    </div>
                    <div className="flex">
                        <p className="w-36">Category : </p>
                        <p>{project.ProjectDetails?.category}</p>
                    </div>
                    <div className="flex">
                        <p className="w-36">Project Lead : </p>
                        <Badge>{project.ProjectDetails?.owner.fullName}</Badge>
                    </div>
                </div>
                <section>
                    <p className="py-5 border-b text-lg -tracking-wider">Tasks</p>
                    <div className="lg:flex md:flex gap-3 justify-between py-5">
                        <IssueList status="pending" title="Todo List"/>
                        <IssueList status="in_progress" title="In progress"/>
                        <IssueList status="Done" title="Done"/>
                    </div>

                </section>
            </div>
        </ScrollArea>
        <div>
            <ChatBox/>
        </div>
    </div>

</div>
</>
  )
}

export default ProjectDetails
