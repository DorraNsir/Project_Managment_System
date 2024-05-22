import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import { Card } from "@/components/ui/card"
import { DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger } from "@/components/ui/dropdown-menu"
import { DropdownMenu } from "@radix-ui/react-dropdown-menu"
import { DotFilledIcon, DotsVerticalIcon } from "@radix-ui/react-icons"


const ProjectCard = () => {
  return (
    <Card className="p-5 w-full lg:max-w-3xl">
        <div className="space-y-5">
            <div className="space-y-2">
                <div className="flex justify-between ">
                    <div className="flex items-center gap-5">
                        <h1 className="cursor-pointer font-bold text-lg">
                            Create Ecommerce Project
                        </h1>
                        <DotFilledIcon/>
                        <p className="text-sm text-violet-500">fullsatck</p>

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
                                <DropdownMenuItem>
                                    Delete
                                </DropdownMenuItem>
                            </DropdownMenuContent>
                        </DropdownMenu>
                    </div>
                </div>

                <p className="text-violet-800 text-sm flex items-center ">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Molestias dolor doloremque accusantium ipsam.</p>
            </div>

            <div className="flex flex-wrap gap-2 items-center">
                {
                    [1,1,1,1].map((item)=><Badge key={item} variant="outline">{"frontend"}</Badge>)
                }

            </div>

        </div>
    </Card>
  )
}

export default ProjectCard
