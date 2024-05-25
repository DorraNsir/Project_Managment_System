import { Avatar, AvatarFallback } from "@/components/ui/avatar"



const UserList = () => {
  return (
    <>
      <div className="space-y-2">
        <div className="border rounded-full">
            <p className="py-2 px-3">{"Dorra"||"Unassignee"}</p>

        </div>
        {[1,1,1,1].map((item)=><div key={item} className="py-2 group hover:bg-violet-200 cursor-pointer flex items-center space-x-4 rounded-md px-4">
            <Avatar>
                <AvatarFallback>
                    D
                </AvatarFallback>
            </Avatar>
            <div className="sapce-y-1">
                <p className="text-sm leading-none  ">@Code with dorra</p>
                <p className="text-sm text-muted-foreground  ">@Codewithdorra</p>
            </div>
        </div>)}
      </div>
    </>
  )
}

export default UserList
