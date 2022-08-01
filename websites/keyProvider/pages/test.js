import { useGetPosts } from "../useRequest"

export default function IndexPage() {
    const { parsed, error } = useGetPosts("/test")

    if (error) return (
        <div>
            <h1>Something went wrong!</h1>
            {error ? <p style={{ color: "red" }}>{error.message}</p> : null}
        </div>
    )
    if (!parsed) return <h1>Loading...</h1>

    return (
        <div className="container">
            <h1>AdTech</h1>
            <p>{parsed.id}</p>
            <p>{parsed.value}</p>
        </div>
    )
}