import { deleteProject } from "@/Redux/Project/Action"
import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import { Card } from "@/components/ui/card"
import { DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger } from "@/components/ui/dropdown-menu"
import { DropdownMenu } from "@radix-ui/react-dropdown-menu"
import { DotFilledIcon, DotsVerticalIcon } from "@radix-ui/react-icons"
import { useEffect } from "react"
import { useDispatch } from "react-redux"
import { useNavigate } from "react-router-dom"


const ProjectCard = ({item}) => {
    const navigate=useNavigate()
    const dispatch=useDispatch()
    const handleDelete=()=>{
        dispatch(deleteProject({projectId : item.id}));
    };

  return (
    <Card className="p-5 w-full lg:max-w-3xl">
        <div className="space-y-5">
            <div className="space-y-2">
                <div className="flex justify-between ">
                    <div className="flex items-center gap-5">
                        <h1 onClick={()=>navigate("/project/"+item.id)} className="cursor-pointer font-bold text-lg">
                            {item.name}
                        </h1>
                        <DotFilledIcon/>
                        <p className="text-sm text-violet-500">{item.category}</p>

                    </div>
                    <div>
                        <DropdownMenu>
                            <DropdownMenuTrigger>
                                <Button className="rounded-full text-violet-300" variant="ghost" size="icon">
                                    <DotsVerticalIcon className="text-violet-900"/>
                                </Button>
                            </DropdownMenuTrigger>
                            <DropdownMenuContent>
                                <DropdownMenuItem>
                                    Update
                                </DropdownMenuItem>
                                <DropdownMenuItem onClick={handleDelete}>
                                    Delete
                                </DropdownMenuItem>
                            </DropdownMenuContent>
                        </DropdownMenu>
                    </div>
                </div>

                <p className="text-violet-800 text-sm flex items-center ">{item.description}</p>
            </div>

            <div className="flex flex-wrap gap-2 items-center">
                {
                    item.tags.map((tag)=><Badge key={tag} variant="outline">{tag}</Badge>)
                }

            </div>

        </div>
    </Card>
  )
}

export default ProjectCard
