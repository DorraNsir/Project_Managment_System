import { AvatarFallback } from "@/components/ui/avatar"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger } from "@/components/ui/dropdown-menu"
import { Avatar } from "@radix-ui/react-avatar"
import { DotsVerticalIcon, PersonIcon } from "@radix-ui/react-icons"
import UserList from "./UserList"

const IssueCard = () => {
  return (
    <div>
      <Card>
        <CardHeader>
            <div className="flex justify-between items-center">
                <CardTitle>
                    CreateNabar
                </CardTitle>
                <DropdownMenu>
                    <DropdownMenuTrigger>
                        <Button className="rounded-full " size="icon" variant="ghost">
                            <DotsVerticalIcon/>
                        </Button>
                    </DropdownMenuTrigger>
                    <DropdownMenuContent>
                        <DropdownMenuItem>In Progress</DropdownMenuItem>
                        <DropdownMenuItem>Done</DropdownMenuItem>
                        <DropdownMenuItem>Edit</DropdownMenuItem>
                        <DropdownMenuItem>Delete</DropdownMenuItem>
                    </DropdownMenuContent>
                </DropdownMenu>
            </div>
        </CardHeader>
        <CardContent className="py-0">
            <div className="flex items-center justify-between">
                <p>EBP - {1}</p>
                <DropdownMenu className="w-[30 rem] border border-red-400">
                    <DropdownMenuTrigger>
                        <Button size="icon"
                         className="bg-violet-700 hover:text-black  rounded-full mb-3">
                            <Avatar>
                                <AvatarFallback>
                                    <PersonIcon className="text-black "/>
                                </AvatarFallback>
                            </Avatar>
                        </Button>
                    </DropdownMenuTrigger>
                    <DropdownMenuContent>
                        <UserList/>
                    </DropdownMenuContent>
                </DropdownMenu>

            </div>
        </CardContent>

      </Card>
    </div>
  )
}

export default IssueCard
