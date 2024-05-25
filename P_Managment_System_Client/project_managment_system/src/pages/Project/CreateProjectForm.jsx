
import { Form, FormControl, FormField, FormItem, FormMessage } from "@/components/ui/form"
import { Input } from "@/components/ui/input"
import {  Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { useForm } from "react-hook-form"
import { tags } from "../ProjectList/ProjectList"
import { Cross1Icon } from "@radix-ui/react-icons"
import { useDispatch } from "react-redux"
import { createProject } from "@/Redux/Project/Action"
import { Dialog, DialogClose } from "@radix-ui/react-dialog"
import { Button } from "@/components/ui/button"
import { DialogTrigger } from "@/components/ui/dialog"

const CreateProjectForm = () => {
    const dispatch=useDispatch();
    const handleTagsChange=(newValue)=>{
        const currentTags=form.getValues("tags")
        const updateTags=currentTags.includes(newValue)?
        currentTags.filter(tag=>tag!==newValue):[...currentTags,newValue];

        form.setValue("tags",updateTags)
    }
    const form=useForm({
        defaultValues:{
            name:"",
            description:"",
            category:"",
            tags:["javascript","react"]
        }
    })
    const onSubmit=(data)=>{
        dispatch(createProject(data))
        console.log("create project data",data)
    }
  return (
    <div>
      <Form {...form}>
        <form className="space-y-5" onSubmit={form.handleSubmit(onSubmit)}>
            <FormField control={form.control} 
            name="name"
            render={({field})=>(
                <FormItem>
                    <FormControl>
                        <Input {...field}
                        type="text"
                        className="border w-full border-violet-400 py-5 px-5"
                        placeholder="project name ..."
                        />
                    </FormControl>
                    <FormMessage/>
                </FormItem>
                ) }
            />
            <FormField control={form.control} 
            name="description"
            render={({field})=>(
                <FormItem>
                    <FormControl>
                        <Input {...field}
                        type="text"
                        className="border w-full border-violet-400 py-5 px-5"
                        placeholder="project description ..."
                        />
                    </FormControl>
                    <FormMessage/>
                </FormItem>
                )}
            />
            <FormField control={form.control} 
            name="category"
            render={({field})=>(
                <FormItem>
                    <FormControl>
                        <Select
                        defaultValue="fullstack"
                        value={field.value}
                        onValueChange={(value)=>{
                            field.onChange(value)
                        }}
                        className="border w-full border-violet-400 py-5 px-5"
                        >
                        <SelectTrigger className="w-full">
                            <SelectValue placeholder="Category"/>
                        </SelectTrigger>
                        <SelectContent>
                            <SelectItem value="fullstack">Full Stack</SelectItem>
                            <SelectItem value="frontend">frontend </SelectItem>
                            <SelectItem value="backend">backend</SelectItem>
                        </SelectContent>
                        </Select>
                    </FormControl>
                    <FormMessage/>
                </FormItem>
                 )}
            />

            <FormField control={form.control} 
            name="tags"
            render={({field})=>(
                <FormItem>
                    <FormControl>
                        <Select
                        onValueChange={(value)=>{
                            handleTagsChange(value)
                        }}
                        className="border w-full border-violet-400 py-5 px-5"
                        >
                        <SelectTrigger className="w-full">
                            <SelectValue placeholder="Tags"/>
                        </SelectTrigger>
                        <SelectContent>
                            {tags.map((item)=>
                            <SelectItem key={item} value={item}>{item}</SelectItem>
                            )}
                        </SelectContent>
                        </Select>
                    </FormControl>
                    <div className="flex gap-1 flex-wrap ">
                        {field.value.map((item)=><div key={item} onClick={()=>handleTagsChange(item)}
                        className="cursor-poiner flex rounded-full items-center gap-2 px-4 py-1">
                            <span className="text-sm">{item}</span>
                            <Cross1Icon className=" cursor-pointer h-3 w-3"/>
                        </div>)}
                    </div>
                    <FormMessage/>
                </FormItem>
                )}
            />
            <DialogClose >
                
                    <Button classeName=" w-full mt-5"  onSubmit={onSubmit} type="submit">create Project</Button>
                
            </DialogClose>
        </form>
      </Form>
    </div>
  )
}

export default CreateProjectForm
